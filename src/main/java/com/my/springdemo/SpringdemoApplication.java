package com.my.springdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import psdi.security.ejb.AccessTokenProviderHomeRemote;
import psdi.security.ejb.AccessTokenProviderRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.Hashtable;

//@ImportResource("classpath:ejbs.xml")
//@SpringBootApplication
@Configuration
@ComponentScan
public class SpringdemoApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringdemoApplication.class, args);









//    ApplicationContext context =
//				new ClassPathXmlApplicationContext("ejbs.xml");
//
//		AccessTokenProviderRemote beanfact = context.getBean("greeterBean",AccessTokenProviderRemote.class);
//
//		try {
////			beanfact.create();
//            beanfact.getBindingName();
////			AccessTokenProviderRemote rt =  beanfact.create();
////			rt.getBindingName();
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//
//		System.out.print("dfdfsfdds");
//		// retrieve configured instance
//		PetStoreService   service = context.getBean("petStore", PetStoreService.class);
//
//// use configured instance
//		List<String> userList = service.getUsernameList();


//        public static void main(String[] args) {
//
//            ConfigurableApplicationContext context = new SpringApplicationBuilder()
//                    .sources(Application.class)
//                    .bannerMode(Banner.Mode.OFF)
//                    .run(args);
//
//            Application app = context.getBean(Application.class);
//            app.start();
//        }
//
//        private void start() {
//            System.out.println("Hello World!");
//        }

	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("hello");
        try{
            Hashtable env = new Hashtable();
            env.put("org.omg.CORBA.ORBClass", "com.ibm.CORBA.iiop.ORB");
            env.put("java.naming.factory.initial", "com.ibm.websphere.naming.WsnInitialContextFactory");
            env.put("java.naming.provider.url", "iiop://cnwbzp1177.cn.dst.ibm.com:2810");
            Context ctx = new InitialContext(env);
//            Object obj = ctx.lookup("ejb/maximo/remote/accesstokenprovider"); can not contain forward splash
//            Object obj = ctx.lookup("java:global/tw_esolution/mboejb/accesstokenprovider!psdi.security.ejb.AccessTokenProviderHomeRemote");
            Object obj = ctx.lookup("syncMaximoDataService");

            AccessTokenProviderHomeRemote home = (AccessTokenProviderHomeRemote) PortableRemoteObject.narrow(obj, AccessTokenProviderHomeRemote.class);
            AccessTokenProviderRemote bean = home.create();
            System.out.println("EJB Output -> " + bean.getBindingName());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

//    @Bean
//    public FactoryBean getLoginManagerFactory(){
//        SimpleRemoteStatelessSessionProxyFactoryBean factory = new SimpleRemoteStatelessSessionProxyFactoryBean();
//        String beanName = "jndi.ejb3.LoginManager";
//        factory.setJndiName(beanName);
////        factory.setBusinessInterface(LoginManager.class);
//        Properties p = new Properties();
//
//
//        p.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
//        p.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
//        p.setProperty("java.naming.provider.url", "jnp:localhost:1099");
//        factory.setJndiEnvironment(p);
//        return factory;
//    }

//    @Bean
//    public MBeanServer mbeanServer() {
//        MBeanServerFactoryBean factory = new MBeanServerFactoryBean();
//        factory.setLocateExistingServerIfPossible(true);
//        factory.afterPropertiesSet();
//        return factory.getObject();
//    }
}
