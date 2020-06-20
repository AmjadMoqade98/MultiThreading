package com.Exalt.MultiThreading.Dom;

import com.Exalt.MultiThreading.Constants;
import com.Exalt.MultiThreading.Mapper.ServerMapper;
import com.Exalt.MultiThreading.Service.ServerService;
import com.devskiller.friendly_id.FriendlyId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ServerDomainService {

    @Autowired
    ServerService serverService;

    @Autowired
    FriendlyId friendlyId;

    @Autowired
    ServerMapper serverMapper;

    public static HashMap<String, ServerDom> serversLocal = new HashMap<String, ServerDom>();

    public void RentServer(String customerId, int space) {
        boolean flagCreate = false;
        boolean flagUpdate = false;
        ServerDom serverDom;
        synchronized (serversLocal) {
            String serverId = checkServer(space);
            if (serverId.equals("-1")) {
                flagCreate = true;
                serverId = friendlyId.createFriendlyId();
                serverDom = new ServerDom();
                serverDom.setActive(false);
                serverDom.setId(serverId);
                serverDom.setRemainingCapacity(Constants.ServerMaximumCapacity - space);
                serversLocal.put(serverId, serverDom);
            } else {
                serverDom = serversLocal.get(serverId);
                serverDom.setRemainingCapacity(serverDom.getRemainingCapacity() - space);
                flagUpdate = true;
            }
        }

        if (flagCreate) {
            serverService.addServer(serverMapper.serverDomToDto(serverDom));
        } else if (flagUpdate) {
            while (!serverDom.isActive()) {
            }
            serverService.updateServer(serverMapper.serverDomToDto(serverDom));
        }
        flagCreate = false;
        flagUpdate = false;
    }

    public String checkServer(int targetSpace) {
        serversLocal = sortByValue(serversLocal);
        for (Map.Entry<String, ServerDom> server : serversLocal.entrySet()) {
            if (server.getValue().getRemainingCapacity() >= targetSpace) {
                return server.getKey();
            }
        }
        return "-1";
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

    public void localServersConfiguration() {
        serverService.getServers().forEach(server -> {
            serversLocal.put(server.getId(), serverMapper.serverDtoToDom(server));
        });
    }
}
