######################################################

templatehsqldb:

A project using in memory hsqldb.
Test Springboot data jpa.

1. Run SpringBootDataBaseApplication
2. Run Postman, import InMemDbTest.postman_collection.json

--> Ref:
https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/

- Posts and Comments are initialized by SpringBootDataBaseApplication.
- Insert with @OneToMany example, SpringBootDataBaseApplication, run().
- Mapper example, UserService, getAllUserCartMapping()
- @JsonIgnore // avoiding StackOverflowError, Comment.java and CartEntity.java




==========================================



Caused by: org.hibernate.MappingException: Repeated column in mapping for entity: com.oopsmails.springboot.template.inmemorydb.hsql.model.CartEntity column: user_id (should be mapped with insert="false" update="false")
	at org.hibernate.mapping.PersistentClass.checkColumnDuplication(PersistentClass.java:830)
	at org.hibernate.mapping.PersistentClass.checkPropertyColumnDuplication(PersistentClass.java:848)
	at org.hibernate.mapping.PersistentClass.checkColumnDuplication(PersistentClass.java:870)
	at org.hibernate.mapping.PersistentClass.validate(PersistentClass.java:605)
	at org.hibernate.mapping.RootClass.validate(RootClass.java:265)
	at org.hibernate.boot.internal.MetadataImpl.validate(MetadataImpl.java:329)
	at org.hibernate.boot.internal.SessionFactoryBuilderImpl.build(SessionFactoryBuilderImpl.java:443)
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:879)

--
--> @OneToMany(cascade=ALL, -->mappedBy="userEntity"<--)
Caused by: org.hibernate.AnnotationException: Associations marked as mappedBy must not define database mappings like @JoinTable or @JoinColumn: com.oopsmails.springboot.template.inmemorydb.hsql.model.UserEntity.carts
	at org.hibernate.cfg.annotations.CollectionBinder.bind(CollectionBinder.java:478)
	at org.hibernate.cfg.AnnotationBinder.processElementAnnotations(AnnotationBinder.java:2140)
	at org.hibernate.cfg.AnnotationBinder.processIdPropertiesIfNotAlready(AnnotationBinder.java:911)
	at org.hibernate.cfg.AnnotationBinder.bindClass(AnnotationBinder.java:738)
	at org.hibernate.boot.model.source.internal.annotations.AnnotationMetadataSourceProcessorImpl.processEntityHierarchies(AnnotationMetadataSourceProcessorImpl.java:245)
	at org.hibernate.boot.model.process.spi.MetadataBuildingProcess$1.processEntityHierarchies(MetadataBuildingProcess.java:222)
	at org.hibernate.boot.model.process.spi.MetadataBuildingProcess.complete(MetadataBuildingProcess.java:265)
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.metadata(EntityManagerFactoryBuilderImpl.java:847)
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:874)

--

--> @JoinColumn
Caused by: org.hibernate.tool.schema.spi.SchemaManagementException: Unable to execute schema management to JDBC target [alter table users add column user_id integer not null]
	at org.hibernate.tool.schema.internal.TargetDatabaseImpl.accept(TargetDatabaseImpl.java:59)
	at org.hibernate.tool.schema.internal.SchemaMigratorImpl.applySqlString(SchemaMigratorImpl.java:431)
	at org.hibernate.tool.schema.internal.SchemaMigratorImpl.applySqlStrings(SchemaMigratorImpl.java:449)
	at org.hibernate.tool.schema.internal.SchemaMigratorImpl.migrateTable(SchemaMigratorImpl.java:253)
	at org.hibernate.tool.schema.internal.SchemaMigratorImpl.doMigrationToTargets(SchemaMigratorImpl.java:170)
	at org.hibernate.tool.schema.internal.SchemaMigratorImpl.doMigration(SchemaMigratorImpl.java:60)
	at org.hibernate.tool.hbm2ddl.SchemaUpdate.execute(SchemaUpdate.java:134)
	at org.hibernate.tool.hbm2ddl.SchemaUpdate.execute(SchemaUpdate.java:101)
	at org.hibernate.internal.SessionFactoryImpl.<init>(SessionFactoryImpl.java:472)
	at org.hibernate.boot.internal.SessionFactoryBuilderImpl.build(SessionFactoryBuilderImpl.java:444)
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:879)

--

@JoinColumn(--> name="user_id" <--, referencedColumnName="userId", insertable=false, updatable=false)
==> should use all lower case!!!

Caused by: org.hibernate.DuplicateMappingException: Table [carts] contains physical column name [user_id] referred to by multiple physical column names: [user_id], [userId]
	at org.hibernate.boot.internal.InFlightMetadataCollectorImpl$TableColumnNameBinding.bindPhysicalToLogical(InFlightMetadataCollectorImpl.java:922)
	at org.hibernate.boot.internal.InFlightMetadataCollectorImpl$TableColumnNameBinding.addBinding(InFlightMetadataCollectorImpl.java:891)
	at org.hibernate.boot.internal.InFlightMetadataCollectorImpl.addColumnNameBinding(InFlightMetadataCollectorImpl.java:961)
	at org.hibernate.boot.internal.InFlightMetadataCollectorImpl.addColumnNameBinding(InFlightMetadataCollectorImpl.java:942)
	at org.hibernate.cfg.Ejb3JoinColumn.addColumnBinding(Ejb3JoinColumn.java:788)
	at org.hibernate.cfg.Ejb3Column.linkWithValue(Ejb3Column.java:369)
	at org.hibernate.cfg.annotations.TableBinder.bindFk(TableBinder.java:695)
	at org.hibernate.cfg.annotations.CollectionBinder.bindCollectionSecondPass(CollectionBinder.java:1561)
	at org.hibernate.cfg.annotations.CollectionBinder.bindOneToManySecondPass(CollectionBinder.java:865)
	at org.hibernate.cfg.annotations.CollectionBinder.bindStarToManySecondPass(CollectionBinder.java:780)
	at org.hibernate.cfg.annotations.CollectionBinder$1.secondPass(CollectionBinder.java:719)
	at org.hibernate.cfg.CollectionSecondPass.doSecondPass(CollectionSecondPass.java:54)
	at org.hibernate.boot.internal.InFlightMetadataCollectorImpl.processSecondPasses(InFlightMetadataCollectorImpl.java:1655)
	at org.hibernate.boot.internal.InFlightMetadataCollectorImpl.processSecondPasses(InFlightMetadataCollectorImpl.java:1623)
	at org.hibernate.boot.model.process.spi.MetadataBuildingProcess.complete(MetadataBuildingProcess.java:278)
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.metadata(EntityManagerFactoryBuilderImpl.java:847)
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:874)

--


Caused by: java.sql.SQLSyntaxErrorException: a FOREIGN KEY constraint already exists on the set of columns: FK7OMDJU5H5L95OEK80YMVB56UQ in statement [alter table carts add constraint FK7omdju5h5l95oek80ymvb56uq foreign key (userid) references users]

--> have to comment out

-- ALTER TABLE carts
-- ADD CONSTRAINT FK_UsersCarts
-- FOREIGN KEY (userId) REFERENCES users(userId);


--> Error: StackOverflowError

"java.lang.StackOverflowError: null
	at java.lang.ClassLoader.defineClass1(Native Method)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:763)
	at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)
	at java.net.URLClassLoader.defineClass(URLClassLoader.java:467)
	at java.net.URLClassLoader.access$100(URLClassLoader.java:73)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:368)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:362)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.net.URLClassLoader.findClass(URLClassLoader.java:361)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:708)
	at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:155)
	at com.fasterxml.jackson.databind.ser.std.CollectionSerializer.serializeContents(CollectionSerializer.java:149)
	at com.fasterxml.jackson.databind.ser.std.CollectionSerializer.serialize(CollectionSerializer.java:112)
	at com.fasterxml.jackson.databind.ser.std.CollectionSerializer.serialize(CollectionSerializer.java:25)
	at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:704)
	at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:689)
	at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:155)
	at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:704)
	at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:689)
	at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:155)

--> spring boot jpa onetomany Infinite recursion (StackOverflowError)
--> use 

--> @JsonIgnore <-- added with @ManyToOne, which is reasonable because there is a userId column in table.
@ManyToOne(optional=false, fetch = FetchType.LAZY)


--> @JsonIgnore <-- This could be optional as long as added with @ManyToOne
@OneToMany(cascade=ALL, fetch = FetchType.LAZY)



====================================================


-- ALTER TABLE carts
-- ADD CONSTRAINT FK_UsersCarts
-- FOREIGN KEY (userId) REFERENCES users(userId);

--> if adding DROP TABLE users CASCADE;

Caused by: org.springframework.jdbc.datasource.init.ScriptStatementFailedException: Failed to execute SQL script statement #1 of URL [file:/C:/projects/intellj-ws/template-hsqldb/target/classes/schema-hsqldb.sql]: DROP TABLE users CASCADE; nested exception is java.sql.SQLSyntaxErrorException: user lacks privilege or object not found: USERS in statement [DROP TABLE users CASCADE]


===> Just comment foreign key creation in sql !!!!

-- Do we need to specify foreign keys while creating the tables manually?
Yes! If you're not using Hibernate's DDL update property (spring.jpa.hibernate.ddl-auto = update), 
and creating the tables manually, then you'll need to specify foreign keys while creating the tables.


