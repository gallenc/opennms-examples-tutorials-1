# GPON example 

to import into pris

import instructions:

https://docs.opennms.com/pris/2.0.0/provision-to-opennms.html




event
POST http://192.168.56.200:8980/opennms/rest/events

Content-Type Application/xml

Accept Application/xml

```
<event><uei>uei.opennms.org/internal/importer/reloadImport</uei>
     <parms><parm>
         <parmName>url</parmName><value>http://192.168.56.200:8000/requisitions/gpon1</value>
     </parm></parms> 
   </event>
```

PRIS gpon example

http://192.168.56.200:8000/requisitions/gpon1

