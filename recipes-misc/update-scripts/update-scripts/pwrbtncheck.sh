#!/bin/sh
# Power off the board when the PMIC power button is pressed (KEY_POWER).

BTN=""
for d in /sys/class/input/event*; do
	grep -q pwrbutton $d/device/name 2>/dev/null && BTN=/dev/input/${d##*/}
done
[ -n "$BTN" ] || { echo "pwrbtn: power button input device not found" >&2; exit 1; }

while true; do
	# struct input_event (32-bit): sec, usec, type, code, value
	BTNVAL=$(hexdump -e '8/2 "%x " "\n"' -n 16 $BTN | grep ' 74 ' | awk '{print $7}')
	if [ "$BTNVAL" = "1" ]; then
		echo "Power button pressed"
		sync
		poweroff
		exit 0
	fi
done
