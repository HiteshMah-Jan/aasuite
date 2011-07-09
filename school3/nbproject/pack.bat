@echo on
pack200 --segment-limit=-1 %1.pack.gz %1
