@echo on
pack200 --repack --strip-debug --segment-limit=-1 %1
