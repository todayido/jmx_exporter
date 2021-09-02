JAVA_OPTS="-javaagent:/Users/momo/IdeaProjects/jmx_exporter/jmx_prometheus_javaagent/target/jmx_prometheus_javaagent-0.16.2-SNAPSHOT.jar=3011:/Users/momo/IdeaProjects/jmx_exporter/example_configs/httpserver_sample_config.yml"

~~~
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/todayido/jmx_exporter.git
git push -u origin main
git remote remove main
~~~