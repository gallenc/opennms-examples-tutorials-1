

Error when creating ticket

```
2024-06-06 08:45:49,456 WARN  [features-6-thread-1] o.o.n.t.OSGiBasedTicketerPlugin: Ticketer plugin org.opennms.features.apilayer.ticketing.TicketingPluginManager$1@43450a2b will not be registered, since org.opennms.features.apilayer.ticketing.TicketingPluginManager$1@43450a2b is already active.
2024-06-06 08:50:49,609 ERROR [OpenNMS.TroubleTicketer-Thread] o.a.c.j.u.JAXRSUtils: No message body writer has been found for class org.ni2.v01.api.tt.model.AuthenticationRequest, ContentType: application/json
2024-06-06 08:50:49,611 WARN  [OpenNMS.TroubleTicketer-Thread] o.a.c.p.PhaseInterceptorChain: Interceptor for {https://demo-deck-apigtw.ni2.tech}WebClient has thrown exception, unwinding now
org.apache.cxf.interceptor.Fault: No message body writer has been found for class org.ni2.v01.api.tt.model.AuthenticationRequest, ContentType: application/json
        at org.apache.cxf.jaxrs.client.WebClient$BodyWriter.doWriteBody(WebClient.java:1228) ~[cxf-rt-rs-client-3.5.5.jar:3.5.5]
        at org.apache.cxf.jaxrs.client.AbstractClient$AbstractBodyWriter.handleMessage(AbstractClient.java:1223) ~[cxf-rt-rs-client-3.5.5.jar:3.5.5]
        at org.apache.cxf.phase.PhaseInterceptorChain.doIntercept(PhaseInterceptorChain.java:307) [cxf-core-3.5.5.jar:3.5.5]
        at org.apache.cxf.jaxrs.client.AbstractClient.doRunInterceptorChain(AbstractClient.java:710) [cxf-rt-rs-client-3.5.5.jar:3.5.5]
        at org.apache.cxf.jaxrs.client.WebClient.doChainedInvocation(WebClient.java:1086) [cxf-rt-rs-client-3.5.5.jar:3.5.5]
        at org.apache.cxf.jaxrs.client.WebClient.doInvoke(WebClient.java:932) [cxf-rt-rs-client-3.5.5.jar:3.5.5]
        at org.apache.cxf.jaxrs.client.WebClient.doInvoke(WebClient.java:901) [cxf-rt-rs-client-3.5.5.jar:3.5.5]
        at org.apache.cxf.jaxrs.client.WebClient.invoke(WebClient.java:364) [cxf-rt-rs-client-3.5.5.jar:3.5.5]
        at org.apache.cxf.jaxrs.client.WebClient.post(WebClient.java:373) [cxf-rt-rs-client-3.5.5.jar:3.5.5]
        at org.ni2.v01.api.tt.client.Ni2TTApiClient.getAuthenticationToken(Ni2TTApiClient.java:99) [!/:?]
        at org.ni2.v01.api.tt.client.Ni2TTApiClient.createTroubleTicket(Ni2TTApiClient.java:137) [!/:?]
        at org.ni2.v01.api.tt.opennms.plugin.Ni2TicketerPlugin.save(Ni2TicketerPlugin.java:100) [!/:?]
        at org.ni2.v01.api.tt.opennms.plugin.Ni2TicketerPlugin.saveOrUpdate(Ni2TicketerPlugin.java:80) [!/:?]
        at Proxy6664d518_98af_41d0_bee5_e04137d7dde0.saveOrUpdate(Unknown Source) [?:?]
        at org.opennms.features.apilayer.ticketing.TicketingPluginManager$1.saveOrUpdate(TicketingPluginManager.java:68) [!/:?]
        at org.opennms.netmgt.ticketd.OSGiBasedTicketerPlugin.saveOrUpdate(OSGiBasedTicketerPlugin.java:60) [org.opennms.features.ticketing.daemon-32.0.5.jar:?]
        at org.opennms.netmgt.ticketd.DefaultTicketerServiceLayer.createTicketForAlarm(DefaultTicketerServiceLayer.java:223) [org.opennms.features.ticketing.daemon-32.0.5.jar:?]
        at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[?:?]
        at jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) ~[?:?]
        at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[?:?]
        at java.lang.reflect.Method.invoke(Method.java:568) ~[?:?]
        at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:302) [org.apache.servicemix.bundles.spring-aop-4.2.9.RELEASE_1.ONMS.1.jar:?]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:190) [org.apache.servicemix.bundles.spring-aop-4.2.9.RELEASE_1.ONMS.1.jar:?]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157) [org.apache.servicemix.bundles.spring-aop-4.2.9.RELEASE_1.ONMS.1.jar:?]
        at org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:99) [org.apache.servicemix.bundles.spring-tx-4.2.9.RELEASE_1.jar:?]
        at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:281) [org.apache.servicemix.bundles.spring-tx-4.2.9.RELEASE_1.jar:?]
        at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:96) [org.apache.servicemix.bundles.spring-tx-4.2.9.RELEASE_1.jar:?]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) [org.apache.servicemix.bundles.spring-aop-4.2.9.RELEASE_1.ONMS.1.jar:?]
        at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:208) [org.apache.servicemix.bundles.spring-aop-4.2.9.RELEASE_1.ONMS.1.jar:?]
        at jdk.proxy2.$Proxy182.createTicketForAlarm(Unknown Source) [?:?]
        at org.opennms.netmgt.ticketd.TroubleTicketer.handleCreateTicket(TroubleTicketer.java:221) [org.opennms.features.ticketing.daemon-32.0.5.jar:?]
        at org.opennms.netmgt.ticketd.TroubleTicketer.onEvent(TroubleTicketer.java:158) [org.opennms.features.ticketing.daemon-32.0.5.jar:?]
        at org.opennms.netmgt.eventd.EventIpcManagerDefaultImpl$EventListenerExecutor$2.run(EventIpcManagerDefaultImpl.java:189) [org.opennms.features.events.daemon-32.0.5.jar:?]
        at java.util.concurrent.CompletableFuture$AsyncRun.run(CompletableFuture.java:1804) [?:?]
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136) [?:?]
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635) [?:?]
        at org.opennms.core.concurrent.LogPreservingThreadFactory$2.run(LogPreservingThreadFactory.java:106) [opennms-util-32.0.5.jar:?]
        at java.lang.Thread.run(Thread.java:833) [?:?]
Caused by: javax.ws.rs.ProcessingException: No message body writer has been found for class org.ni2.v01.api.tt.model.AuthenticationRequest, ContentType: application/json
        at org.apache.cxf.jaxrs.client.AbstractClient.reportMessageHandlerProblem(AbstractClient.java:853) ~[cxf-rt-rs-client-3.5.5.jar:3.5.5]
        at org.apache.cxf.jaxrs.client.AbstractClient.writeBody(AbstractClient.java:539) ~[cxf-rt-rs-client-3.5.5.jar:3.5.5]
        at org.apache.cxf.jaxrs.client.WebClient$BodyWriter.doWriteBody(WebClient.java:1223) ~[cxf-rt-rs-client-3.5.5.jar:3.5.5]
        ... 37 more

```