# Marlin source shared by bbb-marlin (ARM application) and pru-firmware
# (PRU_stepper firmware): both must come from the same revision, since
# they share the block buffer layout in common_pru.h.

SRCREV = "ebf76f824e724c77abb8a94412c88d1d6d72bcfb"
SRCBRANCH = "BB_Marlin"
MARLIN_SRC_URI = "git://github.com/geggio84/Marlin.git;protocol=https;branch=${SRCBRANCH}"
