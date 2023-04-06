timestamp := $(shell /bin/date "+%F %T")

no_default:
	@echo "no default target"

deploy:
	@mvn -f $(CURDIR)/pom.xml clean deploy -P sonar

install:
	@mvn -f $(CURDIR)/pom.xml clean install

github:
	@git add .
	@git commit -m "$(timestamp)"

.PHONY: no_default deploy install github