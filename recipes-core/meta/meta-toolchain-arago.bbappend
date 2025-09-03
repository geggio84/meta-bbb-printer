
arago_sdk_fixup () {
        # Remove broken .la files
        for i in `find ${SDK_OUTPUT}/${SDKPATH} -name \*.la`; do
                rm -f $i
        done

        cleanup_toolchain_packages

        # Remove python binaries
        # gdb links with libpython and loads core modules, which bundled with binaries
        rm -rf ${SDK_OUTPUT}/${SDKPATHNATIVE}${bindir_nativesdk}/python*

        # Do some extra setup work due to new structure
        mkdir -p "${SDK_OUTPUT}/${SDKPATHNATIVE}${prefix_nativesdk}/lib/${TUNE_PKGARCH}${TARGET_VENDOR}-${TARGET_OS}"
        lexec="${SDK_OUTPUT}/${SDKPATHNATIVE}${prefix_nativesdk}/libexec/gcc/${TOOLCHAIN_SYS}"
        tcv=`ls -1 $lexec|head -1`
        ln -s . ${SDK_OUTPUT}/${SDKTARGETSYSROOT}/lib/$tcv
        ln -s . ${SDK_OUTPUT}/${SDKTARGETSYSROOT}/usr/lib/$tcv
        ln -s . ${SDK_OUTPUT}/${SDKTARGETSYSROOT}/lib/${TOOLCHAIN_SYS}
        ln -s . ${SDK_OUTPUT}/${SDKTARGETSYSROOT}/usr/lib/${TOOLCHAIN_SYS}
        tcpath="${SDK_OUTPUT}/${SDKPATHNATIVE}${prefix_nativesdk}/${TOOLCHAIN_SYS}"
        mkdir -p $tcpath
        old_path=$tcpath
        cd $tcpath
        ln -s ${SDKTARGETSYSROOT}/include include
        if [ "${TOOLCHAIN_BRAND}" != "arago" ]; then
                mkdir -p libc
                cd libc
        fi
        mkdir -p usr
        ln -s ${SDKTARGETSYSROOT}/lib lib
        ln -s ${SDKTARGETSYSROOT}/usr/lib usr/lib
        ln -s ${SDKTARGETSYSROOT}/usr/include usr/include
        cd $old_path
}

