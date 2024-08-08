# GPON Correlation scenario currently implemented

# scenario overview


A NOC needs to be informed immediately of problems which are SLA impacting and which they can take actions to remedy. 
However in a major event, the network can produce far too much fault information for operators to analyse manually. 
Automated assistance is required to help diagnose the underlying problem quickly and reduce the visual noise of a 'storm' of sympathetic alarms.

Alarm and event correlation is a process by which a network management system seeks to present users with useful and actionable information from all of the sources of information available to it.

1. reduce the events to a set of alarms which represent the current state of problems in the network
2. create new alarms (or situations) for scenarios where the underlying pattern of alarms point to a specific problem

Scenario:

A GPON fibre network is an access network essentially organised in a tree topology.

At the head-end an OLT (optical line termination) sends multiplexed light signals to multiple  ONT (optical Network termination) consumer units.

OLT devices typically host many OLT's and are themselves connected to an edge router which feeds into the core network. 

ONT's are situated on customer premises and are variously subject to accidental disconnection, power outages or other failures local to the customer. 

ONT alarms need to be recorded and processed by the service management team as they affect the SLA with a customer and may also be required to be reported to the regulator. 
Individual ONT alarms are typically ignored by the NOC as there can be many thousands of them at any one time and they do not necessarily indicate a network wide problem. 
However a pattern of multiple ONT alarms may point to a fibre or OLT problem further up the GPON tree.

Between the ONTs and an OLT are geographically dispersed passive optical splitters which fan out the head-end fibre to each of the ONT's. 
Cityfibre refers to head-end as a  FEX (fibre exchange) and the splitters are arranged in a two tier architecture of PRIMARY and SECONDARY nodes fanning out from the FEX. 
Typically an OLT is connected to one PRIMARY node which fans out to eight SECONDARY nodes which in turn fan out to eight ONTs. 

If a fault happens in a fibre feeding a PRIMARY or SECONDARY node, it will affect communications with all of the OLT's below that point.
This can generate a sea of alarms but the NOC needs to be able to quickly identify where a fibre cut may have happened and ignore the sympathetic alarms it may have created.

The FEX, PRIMARY and SECONDARY nodes may also internally be arranged in a hierarchy of shelves and connectors. 
It is possible that an engineer working on a node may disrupt one of these internal splitters which will cause a fault for some customers but not the entire node.

Fibre cuts will cause a major outages but degradation to the signal path may also create intermittent low signal levels or high bit error rates. 
These sorts of issues need to be detected as they may precede a major problem but should be addressed through regular maintenance and are not necessarily priority problems for the NOC.

Alarms in the access network are typically concerned only with the local GPON and usually propagate sympathetic alarms into the core.
Thus the alarm management of each FEX region can be essentially stand alone from the management of the core.

## required correlation

1. record and correctly associate alarms with affected OLT's and ONT's in such a way is it is possible for later reports to be created on service impact for individual customers

2. record but do not bring to the NOC's attention alarms on individual ONT's

3. record for later reporting alarms where an intermittent problem or degradation may be occurring but which is not currently a major outage.

4. correlate critical ONT alarms across the GPON topology to quickly detect the location of major fibre outages.

## critical alarms and alarm parsing



critical alarms which indicate a fibre break are any of

|CALEX | ont-eth-down,ont-dying-gasp,ont-missing,low-rx-opt-pwr-fe,loss-of-pon,lacp-fault-on-port,lag-group-down,duplex-system-failure,card-departed,lan-los|

|NOKIA |onu-dying-gasp,onu-loss-of-phy-layer,transceiver-link-rx-power|

More detailed breakdown

|Error Condition|OLT alarm| PE Alarm|Notes|

|-----|---|---|---|
|Customer ONT Ethernet Port state change| ont-eth-down / lan-los | CFM defMACstatus |   |
|Power loss at ONT |  ont-dying-gasp / ont departed / onu-dying-gasp | CFM defRemoteCCM    |
|Fibre break beween ONT and OLT | ont-missing / onu-loss-of-phy-layer |CFM defRemoteCCM    Can be at any point between customer premisis and OLT including Primary or Secondary Node in which case multiple customers might be impacted |
|Fibre break between Primary Node and OLT |   loss-of-pon | CFM defRemoteCCM    Might also receive this message if a PON only has a single customer and that customer experiences a fibre break |
|Bent fibre between ONT and OLT | low-rx-opt-pwr-fe / low-rx-opt-pwr-ne / transceiver-link-rx-power |      Can be at any point between customer premises and OLT including Primary or Secondary Node in which case multiple customers might be impacted |
|Failure of PON Optic  |  interface-module-failure |   CFM defRemoteCCM    |
|Failure of OLT linecard |card-departed |  CFM defRemoteCCM    |
|Failure of  single link in bundle interface between OLT and PE|  lacp-fault-on-port | n/a |
|Failure of bundle interface between OLT and PE  lag-group-down | CFM defRemoteCCM   | |
            

## solution options









