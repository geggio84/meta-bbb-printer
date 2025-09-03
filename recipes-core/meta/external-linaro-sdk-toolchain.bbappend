do_install() {
        install -d ${D}${prefix}/${ELT_TARGET_SYS}/bin
        install -d ${D}${prefix}/${ELT_TARGET_SYS}/lib
        install -d ${D}${bindir}
        install -d ${D}${libdir}
        install -d ${D}${prefix}/${ELT_TARGET_SYS}/lib/ldscripts
        install -d ${D}${libexecdir}
        ${@base_conditional('PREFERRED_PROVIDER_gdb-cross-canadian-arm', 'external-linaro-sdk-toolchain', 'install -d ${D}${datadir}/gdb', '', d)}
        ${@base_conditional('PREFERRED_PROVIDER_gdb-cross-canadian-arm', 'external-linaro-sdk-toolchain', 'install -d ${D}${datadir}/info', '', d)}
        ${@base_conditional('PREFERRED_PROVIDER_gdb-cross-canadian-arm', 'external-linaro-sdk-toolchain', 'install -d ${D}${datadir}/man/man1', '', d)}
        install -d ${D}${gcclibdir}/${ELT_TARGET_SYS}/${ELT_VER_GCC}/include

#       cp -a ${TOOLCHAIN_PATH}/${ELT_TARGET_SYS}/bin/{c++,g++,gcc*} ${D}${prefix}/${ELT_TARGET_SYS}/bin
        cp -a ${TOOLCHAIN_PATH}/${ELT_TARGET_SYS}/lib/libstdc++.* ${D}${prefix}/${ELT_TARGET_SYS}/lib
        cp -a ${TOOLCHAIN_PATH}/${ELT_TARGET_SYS}/lib/libgcc_s.* ${D}${prefix}/${ELT_TARGET_SYS}/lib
        cp -a ${TOOLCHAIN_PATH}/${ELT_TARGET_SYS}/lib/libsupc++.* ${D}${prefix}/${ELT_TARGET_SYS}/lib
        cp -a ${TOOLCHAIN_PATH}/lib/gcc/${ELT_TARGET_SYS}/${ELT_VER_GCC}/* ${D}${gcclibdir}/${ELT_TARGET_SYS}/${ELT_VER_GCC}
        cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}gcov ${D}${bindir}
        cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}gcc* ${D}${bindir}
        cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}g++ ${D}${bindir}
        cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}cpp ${D}${bindir}
        cp -a ${TOOLCHAIN_PATH}/libexec/* ${D}${libexecdir}

        ${@base_conditional('PREFERRED_PROVIDER_gdb-cross-canadian-arm', 'external-linaro-sdk-toolchain', 'cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}gdb* ${D}${bindir}', '', d)}
        ${@base_conditional('PREFERRED_PROVIDER_gdb-cross-canadian-arm', 'external-linaro-sdk-toolchain', 'cp -a ${TOOLCHAIN_PATH}/share/gdb/* ${D}${datadir}/gdb/', '', d)}
        ${@base_conditional('PREFERRED_PROVIDER_gdb-cross-canadian-arm', 'external-linaro-sdk-toolchain', 'cp -a ${TOOLCHAIN_PATH}/share/info/* ${D}${datadir}/info/', '', d)}
        ${@base_conditional('PREFERRED_PROVIDER_gdb-cross-canadian-arm', 'external-linaro-sdk-toolchain', 'cp -a ${TOOLCHAIN_PATH}/share/man/man1/${TARGET_PREFIX}* ${D}${datadir}/man/man1/', '', d)}

        cp -a ${TOOLCHAIN_PATH}/${ELT_TARGET_SYS}/bin/ld* ${D}${prefix}/${ELT_TARGET_SYS}/bin
        cp -a ${TOOLCHAIN_PATH}/${ELT_TARGET_SYS}/bin/objcopy ${D}${prefix}/${ELT_TARGET_SYS}/bin
        cp -a ${TOOLCHAIN_PATH}/${ELT_TARGET_SYS}/bin/strip ${D}${prefix}/${ELT_TARGET_SYS}/bin
        cp -a ${TOOLCHAIN_PATH}/${ELT_TARGET_SYS}/bin/nm ${D}${prefix}/${ELT_TARGET_SYS}/bin
        cp -a ${TOOLCHAIN_PATH}/${ELT_TARGET_SYS}/bin/ranlib ${D}${prefix}/${ELT_TARGET_SYS}/bin
        cp -a ${TOOLCHAIN_PATH}/${ELT_TARGET_SYS}/bin/as ${D}${prefix}/${ELT_TARGET_SYS}/bin
        cp -a ${TOOLCHAIN_PATH}/${ELT_TARGET_SYS}/bin/ar ${D}${prefix}/${ELT_TARGET_SYS}/bin
        cp -a ${TOOLCHAIN_PATH}/${ELT_TARGET_SYS}/bin/objdump ${D}${prefix}/${ELT_TARGET_SYS}/bin
        cp -a ${TOOLCHAIN_PATH}/${ELT_TARGET_SYS}/lib/ldscripts/* ${D}${prefix}/${ELT_TARGET_SYS}/lib/ldscripts
        cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}ld* ${D}${bindir}
        cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}addr2line ${D}${bindir}
        cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}objcopy ${D}${bindir}
        cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}readelf ${D}${bindir}
        cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}strip ${D}${bindir}
        cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}nm ${D}${bindir}
        cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}ranlib ${D}${bindir}
        cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}gprof ${D}${bindir}
        cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}as ${D}${bindir}
        cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}c++filt ${D}${bindir}
        cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}ar ${D}${bindir}
        cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}strings ${D}${bindir}
        cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}objdump ${D}${bindir}
        cp -a ${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}size ${D}${bindir}
}

