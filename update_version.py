import xml.dom.minidom as md

file = md.parse("pom.xml")

current_version = file.getElementsByTagName("version")[0].firstChild.nodeValue

versions = []
for version in (current_version.split(".")):
    versions.append(version)

patch = str(int(versions[2]) + 1)
versions[2] = patch

joined_version = ".".join(versions)

file.getElementsByTagName("version")[0].firstChild.nodeValue = joined_version

with open("pom.xml", "w") as fs:
    fs.write(file.toxml())
    fs.close()
