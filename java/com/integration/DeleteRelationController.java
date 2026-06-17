
package java.com.integration;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class DeleteRelationController {

    @PostMapping("/delete-relation")
    public ResponseEntity<?> deleteRelation(@RequestBody RequestBody req) {

        try {
            String url = "https://app.onetrust.com/api/inventory/v2/inventories/"
                    + req.inventoryId + "/relations";

            String jsonBody = "[{\"inventoryAssociationId\":\"" 
                    + req.associationId + "\"}]";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(req.accessToken);

            HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.DELETE,
                    entity,
                    String.class
            );

            return ResponseEntity.ok("✅ Deleted");

        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
