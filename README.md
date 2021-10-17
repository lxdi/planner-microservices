## troubleshooting
- No logs in docker/temp/logs -> extend rights on this folder


## Docker
## Remove images
docker rmi $(docker images planner2/* -q) -f
docker rmi $(docker images -f dangling=true -q) -f