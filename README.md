# ac_infinity_bluetooth
Resources for BT communication with AC Infinity controller units 67/69/70

## Resources

* [ACInfinity_1.5.8_apktool](ACInfinity_1.5.8_apktool) AC Infinity Android application 1.5.8 run through APKtool
* [ACInfinity_1.5.8_JADX](ACInfinity_1.5.8_JADX) AC Infinity Android application 1.5.8 run through JADX

## Areas of interest

* [BluetoothKey.java](ACInfinity_1.5.8_JADX/sources/com/eternal/base/global/BluetoothKey.java) Public class defintion
* [BaseProtocol.java](ACInfinity_1.5.8_JADX/sources/com/eternal/base/protocol/BaseProtocol.java) Base class for BT communication, mostly helper functions
* [CFamilialResolution.java](ACInfinity_1.5.8_JADX/sources/com/eternal/base/protocol/CFamilialResolution.java) BT frame parsing and field definitions
* [ProtocolResolution.java](ACInfinity_1.5.8_JADX/sources/com/eternal/base/protocol/ProtocolResolution.java) More BT frame parsing and field definitions
* [ProtocolTransformer.java](ACInfinity_1.5.8_JADX/sources/com/eternal/base/protocol/ProtocolTransformer.java) Data transformation helper functions

## Test setup

* RPi 4 running Raspbian Bullseye
* Built-in BT chipset
* AC Infinity Controller 69

## Test process

```bash
bluetoothctl
discoverable on
scan on
# Wait for discovery packet from ACI-E like: Device CE:CA:C1:12:34:56 ACI-E
connect CE:CA:C1:12:34:56
trust CE:CA:C1:12:34:56
info CE:CA:C1:12:34:56
menu gatt
list-attributes
```