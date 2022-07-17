package com.camelot.p44camelotbmw.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
public class createShipmentController {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    @PostMapping(value = "/v1/createShipment", consumes = "application/json")
    //@KafkaListener(topics = "p44Data", groupId = "vesselP44")
    public ResponseEntity<?> shipmentDetailsFromP44(@RequestBody String message) {
        message = message.trim().replaceAll("\r\n", "");
        String sql = "INSERT INTO public.recievedjsonp44 (shipmentjson) VALUES ('" + message + "')";
        //int rows = jdbcTemplate.update(sql);
//        int rows1 = jdbcTemplate.query(sql, new String[] { username });
//        ,
//	         (ResultSet rs, int rowNum) -> {
//
//	        	 UsersPojo user1 = new UsersPojo();
//	         user1.setUsername(username);
//	         user1.setPassword(rs.getString("PASSWORD"));
//	         return user1;
//	      });
//        if (rows > 0) {
//            System.out.println("Insert successful");
//        }
        return ResponseEntity.ok().build();
    }

}
