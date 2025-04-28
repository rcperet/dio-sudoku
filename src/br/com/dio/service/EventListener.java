package br.com.dio.service;

import br.com.dio.enums.EventEnum;

public interface EventListener {
	
	void update(final EventEnum eventType);

}
