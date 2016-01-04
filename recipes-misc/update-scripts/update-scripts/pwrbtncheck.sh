#!/bin/sh

BTN=/dev/input/event2

while true; do
  BTNVAL=`hexdump -e '8/2 "%x " "n"' -n 16 $BTN | grep ' 74 ' | awk '{print $7}'`
  if [ "$BTNVAL" = "1" ]
  then
    echo "Power button pressed"
    /bin/sync; /bin/sync; /sbin/shutdown -h now
    exit 0
  fi
  sleep 1
done
