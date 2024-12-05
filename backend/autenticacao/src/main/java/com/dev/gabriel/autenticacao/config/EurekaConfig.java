package com.dev.gabriel.autenticacao.config;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EurekaConfig {
  private final EurekaClient eurekaClient;
  private final ApplicationInfoManager applicationInfoManager;

  @EventListener(ApplicationReadyEvent.class)
  public void reconnectToEureka() {
    try {
      this.applicationInfoManager.setInstanceStatus(InstanceInfo.InstanceStatus.UP);
      this.eurekaClient.getApplicationInfoManager().refreshDataCenterInfoIfRequired();
    } catch (Exception e) {
      log.error("Reconexão com o Eureka Server falhou: " + e.getMessage(), e);
    }
  }
}
