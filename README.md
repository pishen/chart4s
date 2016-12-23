# chart4s

Enter sbt console, and

```
scala> import chart4s._
import chart4s._

scala> import ChartBuilders._
import ChartBuilders._

scala> implicit val server = new ChartServer()
11:35:19.512 [run-main-0] DEBUG p.a.l.concurrent.ActorSystemProvider - Starting application default Akka system: application
11:35:19.536 [run-main-0] INFO  play.api.Play - Application started (Prod)
11:35:19.670 [run-main-0] INFO  play.core.server.NettyServer - Listening for HTTP on /0:0:0:0:0:0:0:0:9000
server: chart4s.ChartServer = chart4s.ChartServer@18c5cb4f
```

Now, open your browser at `http://localhost:9000`, then

```
scala> Seq(3,6,2,10,2).toLineChart.draw
```
