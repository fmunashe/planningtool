package za.co.sabs.planningtool.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = false) // use CGLIB class proxies to avoid JDK-proxy type clashes
public class AopConfig {
}
