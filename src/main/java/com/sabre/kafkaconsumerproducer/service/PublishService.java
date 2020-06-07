/**
 * @author D. Scott Sabre
 * @since 2020
 * All rights reserved. This software is proprietary property owned by the author. This software is fully
 * available for reuse, however if the contents remain wholly or substantially intact, the user agrees
 * to keep this statement in that content. This software is offered "as-is" and without warranty, and the
 * author assumes no liability for damages resulting from use. The user shall shall use this software only
 * in accordance with these terms.
 */

package com.sabre.kafkaconsumerproducer.service;

import com.sabre.kafkaconsumerproducer.model.Demo;

public interface PublishService {
	
	public void publishMessage(Demo demo);

}
