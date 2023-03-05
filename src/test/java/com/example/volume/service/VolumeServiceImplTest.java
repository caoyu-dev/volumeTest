package com.example.volume.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.List;

class VolumeServiceImplTest {
    private final String volumeResponse = "{\"volumes\":[{\"attachments\":[{\"server_id\":\"aaaabcd\"}]},{\"attachments\":[]},{\"attachments\":[]},{\"attachments\":[]},{\"attachments\":[]},{\"attachments\":[{\"server_id\":\"bcddddd\"}]},{\"attachments\":[{\"server_id\":\"ccdbwww\"}]},{\"attachments\":[]},{\"attachments\":[]},{\"attachments\":[{\"server_id\":\"eegwsddd\"}]},{\"attachments\":[]}]}";

    // SELECT ID FROM TBMB_OSK_SERVER WHERE TYPE NOT IN ('SERVER', 'ADMIN') AND MEM_SQ = 'seq'

    @Test
    void test() {
        List<String> otherServiceVmId = List.of(
                "aaaabcd",
                "eegwsddd"
        );

        JSONObject createVolumeResponse = new JSONObject(volumeResponse);
        JSONArray volumes = createVolumeResponse.getJSONArray("volumes");

        JSONArray volumeTmp = new JSONArray();
        for (int i = 0; i < volumes.length(); i++) {
            JSONArray attachments = volumes.getJSONObject(i).getJSONArray("attachments");
            if (!attachments.isEmpty()) {
                String serverId = attachments.getJSONObject(0).getString("server_id");
                for (int j = 0; j < otherServiceVmId.size(); j++) {
                    if (otherServiceVmId.get(j).equals(serverId)) {
                        volumeTmp.put(volumes.getJSONObject(i));
                    }
                }
            }
        }

        for (int i = 0; i < volumes.length(); i++) {
            for (int j = 0; j < volumeTmp.length(); j++) {
                if (volumes.get(i) == volumeTmp.get(j)) {
                    volumes.remove(i);
                }
            }
        }

        System.out.println("**** volumes --> " + volumes);
    }
}