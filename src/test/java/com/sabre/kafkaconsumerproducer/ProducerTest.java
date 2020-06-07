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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.sabre.kafkaconsumerproducer.message.config.KafkaProducerConfig;
import com.sabre.kafkaconsumerproducer.model.Category;
import com.sabre.kafkaconsumerproducer.model.Demo;
import com.sabre.kafkaconsumerproducer.model.Demo.StatusEnum;
import com.sabre.kafkaconsumerproducer.model.Tag;
import com.sabre.kafkaconsumerproducer.service.PublishService;
import com.sabre.kafkaconsumerproducer.service.impl.PublishServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PublishServiceImpl.class, KafkaProducerConfig.class})
@DirtiesContext
public class ProducerTest {

	@Autowired
	private PublishService publishService;
	
	@Test
	public void testKafkaAdminConfig() {
		//Not a true test -- check topic and server in application.properties for results!
		publishService.publishMessage(createDemoModelForTest());
	}
	
	private Demo createDemoModelForTest() {
		
		List<Tag> tags = new ArrayList<>(); 
		tags.add(new Tag().id(111L).name("Tag 1 Name"));
		tags.add(new Tag().id(222L).name("Tag 2 Name"));
		return new Demo()
				.category(new Category().id(333L).name("Category Name"))
				.id(444L)
				.name("Demo Name")
				.status(StatusEnum.PENDING)
				.tags(tags);
	}
	

}
