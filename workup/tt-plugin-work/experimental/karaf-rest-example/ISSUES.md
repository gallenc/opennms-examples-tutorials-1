# TODO problems with depencencies in OpenNMS

```
admin@opennms()> feature:install karaf-rest-example-client-cxf
Error executing command: Unable to resolve root: missing requirement [root] osgi.identity; osgi.identity=karaf-rest-example-client-cxf; type=karaf.feature; version="[4.3.10.SNAPSHOT,4.3.10.SNAPSHOT]"; filter:="(&(osgi.identity=karaf-rest-example-client-cxf)(type=karaf.feature)(version>=4.3.10.SNAPSHOT)(version<=4.3.10.SNAPSHOT))" [caused by: Unable to resolve karaf-rest-example-client-cxf/4.3.10.SNAPSHOT: missing requirement [karaf-rest-example-client-cxf/4.3.10.SNAPSHOT] osgi.identity; osgi.identity=org.apache.karaf.examples.karaf-rest-example-client-cxf; type=osgi.bundle; version="[4.3.10.SNAPSHOT,4.3.10.SNAPSHOT]"; resolution:=mandatory [caused by: Unable to resolve org.apache.karaf.examples.karaf-rest-example-client-cxf/4.3.10.SNAPSHOT: missing requirement [org.apache.karaf.examples.karaf-rest-example-client-cxf/4.3.10.SNAPSHOT] osgi.wiring.package; filter:="(&(osgi.wiring.package=com.fasterxml.jackson.jaxrs.json)(version>=2.14.0)(!(version>=3.0.0)))"]]

admin@opennms()> feature:install karaf-rest-example-client-jersey
Error executing command: Unable to resolve root: missing requirement [root] osgi.identity; osgi.identity=karaf-rest-example-client-jersey; type=karaf.feature; version="[4.3.10.SNAPSHOT,4.3.10.SNAPSHOT]"; filter:="(&(osgi.identity=karaf-rest-example-client-jersey)(type=karaf.feature)(version>=4.3.10.SNAPSHOT)(version<=4.3.10.SNAPSHOT))" [caused by: Unable to resolve karaf-rest-example-client-jersey/4.3.10.SNAPSHOT: missing requirement [karaf-rest-example-client-jersey/4.3.10.SNAPSHOT] osgi.identity; osgi.identity=org.apache.karaf.examples.karaf-rest-example-client-jersey; type=osgi.bundle; version="[4.3.10.SNAPSHOT,4.3.10.SNAPSHOT]"; resolution:=mandatory [caused by: Unable to resolve org.apache.karaf.examples.karaf-rest-example-client-jersey/4.3.10.SNAPSHOT: missing requirement [org.apache.karaf.examples.karaf-rest-example-client-jersey/4.3.10.SNAPSHOT] osgi.wiring.package; filter:="(&(osgi.wiring.package=com.fasterxml.jackson.jaxrs.json)(version>=2.14.0)(!(version>=3.0.0)))"]]
admin@opennms()> feature:install karaf-rest-example-client-http

admin@opennms()> feature:install karaf-rest-example-blueprint
Error executing command: Error:
        Error downloading mvn:org.codehaus.jettison/jettison/1.5.3
        Error downloading mvn:org.apache.cxf/cxf-rt-rs-client/3.5.5
        Error downloading mvn:org.apache.cxf/cxf-rt-rs-service-description/3.5.5
        Error downloading mvn:org.apache.cxf/cxf-rt-frontend-jaxrs/3.5.5
        Error downloading mvn:org.apache.cxf/cxf-rt-rs-json-basic/3.5.5
        Error downloading mvn:org.apache.cxf/cxf-rt-rs-extension-search/3.5.5
        Error downloading mvn:org.apache.cxf/cxf-rt-rs-extension-providers/3.5.5

```