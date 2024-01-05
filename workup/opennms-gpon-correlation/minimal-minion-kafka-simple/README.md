
# GPON example - no frr router

This example doesn't use frr router and simply shows splices as downstream nodes

using [RESTer plugin For Chrome](https://chromewebstore.google.com/detail/rester/eejfoncpjfgmeleakejdcanedmefagga)
[RESTer plugin For Firefox](https://addons.mozilla.org/en-GB/firefox/addon/rester/)


PRIS is used as source of data

to import from pris use the following event ( change address as neccessary)

import instructions:

https://docs.opennms.com/pris/2.0.0/provision-to-opennms.html


event

POST http://192.168.56.200:8980/opennms/rest/events

Content-Type Application/xml

Accept Application/xml

```
<event><uei>uei.opennms.org/internal/importer/reloadImport</uei>
     <parms><parm>
         <parmName>url</parmName><value>http://pris:8000/requisitions/gpon1</value>
     </parm></parms> 
   </event>
```

PRIS gpon example

http://192.168.56.200:8000/requisitions/gpon1

