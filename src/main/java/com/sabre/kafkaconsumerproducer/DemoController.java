/**
 * @author D. Scott Sabre
 * @since 2020
 * All rights reserved. This software is proprietary property owned by the author. This software is fully
 * available for reuse, however if the contents remain wholly or substantially intact, the user agrees
 * to keep this statement in that content. This software is offered "as-is" and without warranty, and the
 * author assumes no liability for damages resulting from use. The user shall shall use this software only
 * in accordance with these terms.
 */
package com.sabre.kafkaconsumerproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sabre.kafkaconsumerproducer.model.Demo;
import com.sabre.kafkaconsumerproducer.service.PublishService;

@RestController
@RequestMapping(value = "/demo")
public class DemoController {

    private final PublishService producer;

    @Autowired
    DemoController(PublishService producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/publish", consumes = {"application/json"}, produces = {"application/json"})
    public String sendMessageToKafkaTopic(@RequestBody Demo somethingToPublish) {
        
    	this.producer.publishMessage(somethingToPublish);
    	return "Message Published!";
    	
    }
}