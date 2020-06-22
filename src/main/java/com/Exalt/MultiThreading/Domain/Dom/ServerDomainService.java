package com.Exalt.MultiThreading.Domain.Dom;

import com.Exalt.MultiThreading.Domain.Constants;
import com.Exalt.MultiThreading.Domain.Dto.CustomerDto;
import com.Exalt.MultiThreading.Domain.Mapper.ServerMapper;
import com.Exalt.MultiThreading.Domain.Service.CustomerService;
import com.Exalt.MultiThreading.Domain.Service.ServerService;
import com.Exalt.MultiThreading.Runnable.ServerSpan;
import com.devskiller.friendly_id.FriendlyId;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ServerDomainService {

    @Autowired
    ServerService serverService;

    @Autowired
    CustomerService customerService;

    @Autowired
    FriendlyId friendlyId;

    @Autowired
    ServerMapper serverMapper;

    @Autowired
    ServerSpan serverSpan;

    public static HashMap<String, ServerDom> serversLocal = new HashMap<String, ServerDom>();

    enum AllocationMethod {
        Update, Span
    }

    public void RentServer(String customerId, int space) {
        AllocationMethod allocationMethod;
        String serverId = checkAvailableServer(space);
        allocationMethod = (serverId.equals("-1")) ?
                AllocationMethod.Span : AllocationMethod.Update;

        ServerDom serverDom = rentLocally(serverId, space, allocationMethod);

        // updating customer space
        CustomerDto customerDto = customerService.getCustomer(customerId);
        customerDto.setReservedSpace(customerDto.getReservedSpace() + space);
        customerService.updateCustomer(customerDto);


        // spanning the server
        if (allocationMethod == AllocationMethod.Span) {
            serverSpan.setServerDto(serverMapper.serverDomToDto(serverDom));
            Thread spanThread = new Thread(serverSpan);
            spanThread.start();
        }
        //allocate space in existing server
        else if (allocationMethod == AllocationMethod.Update) {
            while (!serverDom.isActive()) {
            }
            serverService.updateServer(serverMapper.serverDomToDto(serverDom));
        }
    }

    public String checkAvailableServer(int targetSpace) {
        serversLocal = sortByValue(serversLocal);
        for (Map.Entry<String, ServerDom> server : serversLocal.entrySet()) {
            if (server.getValue().getRemainingCapacity() >= targetSpace) {
                return server.getKey();
            }
        }

        //return -1 if there is no available server with enough space
        return "-1";
    }

    @Async
    public ServerDom rentLocally(String serverId, int space, AllocationMethod allocationMethod) {
        ServerDom serverDom = null;
        // span new server
        if (allocationMethod == AllocationMethod.Span) {
            serverId = friendlyId.createFriendlyId();
            serverDom = new ServerDom();
            serverDom.setActive(false);
            serverDom.setId(serverId);
            serverDom.setRemainingCapacity(Constants.ServerMaximumCapacity - space);
            serversLocal.put(serverId, serverDom);
        }
        //Allocate space from existing server
        else if (allocationMethod == AllocationMethod.Update) {
            serverDom = serversLocal.get(serverId);
            serverDom.setRemainingCapacity(serverDom.getRemainingCapacity() - space);
        }
        return serverDom;
    }

    public static HashMap<String, ServerDom> sortByValue(HashMap<String, ServerDom> hm) {
        List<Map.Entry<String, ServerDom>> list =
                new LinkedList<>(hm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, ServerDom>>() {
            public int compare(Map.Entry<String, ServerDom> o1,
                               Map.Entry<String, ServerDom> o2) {
                return ((Integer) o1.getValue().getRemainingCapacity())
                        .compareTo((Integer) o2.getValue().getRemainingCapacity());
            }
        });

        HashMap<String, ServerDom> temp = new LinkedHashMap<>();
        for (Map.Entry<String, ServerDom> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public void addServerLocal(ServerDom serverDom) {
        serversLocal.put(serverDom.getId(), serverDom);
    }

    public void deleteServerLocal(String id) {
        serversLocal.remove(id);
    }

    public void clearServersLocal() {
        serversLocal.clear();
    }

    public void updateServerLocal(ServerDom serverDom) {
        serversLocal.put(serverDom.getId(), serverDom);
    }

    public void activateServerLocal(String id) {
        serversLocal.get(id).setActive(true);
    }
}