# regex examples

see https://regex101.com/

Syslog examples: 

https://www.calix.com/content/dam/calix/mycalix-misc/lib/iae/axos/21x/mmtg/index.htm?toc9782322.htm?106614.htm

```
This example from https://www.calix.com/content/dam/calix/mycalix-misc/lib/iae/axos/21x/mmtg/index.htm?toc.htm?95527.htm
AXOS R21.x Monitoring, Maintenance, and Troubleshooting Guide

<187>Feb 12 11:12:08 Hikari notfmgrd[5345]: [1][1][A][5345] [23] Id:1201, Syslog-Severity:3, Perceived-Severity:Major, Name:loss-of-signal, Category:PORT Cause:This alarm is set when there is no signal present on an enabled ethernet interface, Details:Interface operationally down, Xpath:/config/shelf[shelf-id='1']/slot[slot-id='1']/interface/ethernet[port='x2'] Address:/interfaces/interface[name='1/1/x2'], Primary-element:NULL, Value:NULL, Verb:NULL, Session:0, Login:NULL, IpAddress:NULL, SrcManager:NULL


find priority PRI
<(.*)>

find month
>(.*)\s

find month day and time
>(\D{3})\s(\d{2})\s(.{8})

find node name
(?<=:[0-9]{2}\s)(.*)(?=\snotfmgrd)

Group 1 Hikari 

find shelfId  slotId activeState




find id
Id:(.*?),
Group1 1201

find severity
(?s).*?Syslog-Severity:([0-9]+).*?
Group 1 3



find percieved-severty
Perceived-Severity:(.*?),

Group1 Major

find name

Name:(.*?),
Group1 loss of signal

find category
Category:(.*?)\s
Group1 PORT

find cause
Cause:(.*?),
Group1 This alarm is set when there is no signal present on an enabled ethernet interface

find details
Details:(.*?),
Group 1 Interface operationally down

find Xpath
Xpath:(.*?)\s
Group1 /config/shelf[shelf-id='1']/slot[slot-id='1']/interface/ethernet[port='x2']

find address
\sAddress:(.*?),
Group1 /interfaces/interface[name='1/1/x2']
```

## full regex example

Full regex - this will match whether or not here is a PRI

(?s)(<(.*?)>|)(\D{3})\s(\d|\d{2})\s(.{8})\s(.*?)(?=\snotfmgrd)\s(?:.*?)\s\[(.*?)\]\[(.*?)\]\[(.*?)\]\[(.*?)\]\s\[(.*?)\]\sId:(.*?),\sSyslog-Severity:([0-9]+),\sPerceived-Severity:(.*?),\sName:(.*?),\sCategory:(.*?)\sCause:(.*?),\sDetails:(.*?),\sXpath:(.*?)\sAddress:(.*?),\s(.*)

```
| tag | group | value | notes |
| --- | --- | --- | --- |
| match| Group 0 | ```<187>Feb 12 11:12:08 Hikari notfmgrd[5345]: [1][1][A][5345] [23] Id:1201, Syslog-Severity:3, Perceived-Severity:Major, Name:loss-of-signal, Category:PORT Cause:This alarm is set when there is no signal present on an enabled ethernet interface, Details:Interface operationally down, Xpath:/config/shelf[shelf-id='1']/slot[slot-id='1']/interface/ethernet[port='x2'] Address:/interfaces/interface[name='1/1/x2'], Primary-element:NULL, Value:NULL, Verb:NULL, Session:0, Login:NULL, IpAddress:NULL, SrcManager:NULL``` | |
| pri | Group 1 | <187> | |
| priValue | Group 2 | 187  |(or empty if no PRI <187>|
| month | Group 3  | Feb | |
| day | Group 4 |  12  | (matched for date of 1 character or 2 characters i.e. Feb 1 or Feb 20)|
| timestamp | Group 5 |  11:12:08 | |
| nodename | Group 6 |  Hikari | |
| shelfId | Group 7  | 1 | |
| slotId | Group 8  | 1 | |
| activeOrStandby | Group 9  | A | |
| processId | Group 10  | 5345 | |
| logFacility | Group 11  | 23 |  log facility (LOG_LOCAL7) |
| Id | Group 12  | 1201 | |
| syslogSeverity | Group 13  | 3 | |
| perceivedSeverity | Group 14 |  Major | |
| name | Group 15  | loss-of-signal | |
| category | Group 16  | PORT | |
| cause | Group 17 |  This alarm is set when there is no signal present on an enabled ethernet interface | |
| details | Group 18  | Interface operationally down | |
| xpath | Group 19  | /config/shelf[shelf-id='1']/slot[slot-id='1']/interface/ethernet[port='x2'] | |
| address | Group 20  | /interfaces/interface[name='1/1/x2'] | |
| additional | Group 21  | Primary-element:NULL, Value:NULL, Verb:NULL, Session:0, Login:NULL, IpAddress:NULL, SrcManager:NULL | |
