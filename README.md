>#### About
This is basic Hibernate fully `XML based configuration` console Application ,implemented using below jar
- hibernate-core -5.0.11
- hibernate-JPA -2.1 
- hibernate-commons-annotations-5.0.1
- Java SE 1.7
>[doc](https://docs.jboss.org/hibernate/entitymanager/3.5/reference/en/html/architecture.html)

#### steps while creating this application.
  - this Java SE project not an maven project here we add jar file mannualy in build path .
  - first we have created hibernate configuration file i.e hibernate.cfg.xml file 
  - this configuration file contain connection properties, hibernate properties, mapping file names.
  - then created mapping file refference in the configuration file.
  - in this application we have perform basic Student CURD operation so created mapping file as Student.hbm.xml.
  - then we have created HibernateUtility where Configutaion objcet is created which reffere to above hibernate.cfg.xml file  
  - and then `SessionFactory` Implemented Object  we get through cfg object refference .
>  SessionFactory provides session instances and all instances are configured to connect to the same database .						
>  The JPA, EntityManagerFactory interface is similar to the SessionFactory in native Hibernate.
  - from sessionFactory object refference we get Session Object 
 >The Session API is used to access a database in a particular unit of work. 
 >It is used to create and remove persistent entity instances, to find entities by their primary key identity,
 > and to query over all entities. JPA EntityManager interface is similar to the Session in Hibernate.
  
 
  ##### Hibernate.cfg.xml
  ```
  <?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
		"-//Hibernate/Hibernate Configuration DTD 3.0//EN"
		"http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
        <property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
        <property name="hibernate.connection.password">root</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/database scema name?createDatabaseIfNotExist=true</property>
	<!--  if we set createDatabaseIfNotExist=true then whenever we run/restart the application it drop existing database schema and create new even if existing schema contain tables it will drop it.
	by defaul createDatabaseIfNotExist is false -->
        <property name="hibernate.connection.username">root</property>
        
        <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
        <property name="hbm2ddl.auto">create</property>
    	<property name="show_sql">true</property>
    	<property name="format_sql">true</property>
    	
    	<mapping resource="com/model/Student.hbm.xml"/>
    </session-factory>
</hibernate-configuration>

```

 ##### Student.hbm.xml
  ```
  <?xml version="1.0"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
<!-- Generated Feb 13, 2018 11:53:59 AM by Hibernate Tools 3.4.0.CR1 -->
<hibernate-mapping>
    <class name="com.model.Student" table="STUDENT">
        <id name="id" type="int">
            <column name="ID"/>
            <generator class="increment" />
        </id>
        <property name="name" type="java.lang.String">
            <column name="NAME" />
        </property>
        <property name="address" type="java.lang.String">
            <column name="ADDRESS" />
        </property>
    </class>
</hibernate-mapping>

  ```
##### HibernateUtility	
```
Configuration cfg = new Configuration();
cfg.configure();
SessionFactory sf = cfg.buildSessionFactory();
Session session = sf.openSession();

```
