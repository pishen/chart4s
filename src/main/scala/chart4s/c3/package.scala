package chart4s

import java.time._
import java.time.format._

package object c3 {
  implicit val localDate2AxisTime = new AxisTime[LocalDate] {
    def format(x: LocalDate) = x.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    def pattern = "%Y-%m-%d"
  }
  
  implicit val localDateTime2AxisTime = new AxisTime[LocalDateTime] {
    def format(x: LocalDateTime) = x.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    def pattern = "%Y-%m-%d %H:%M:%S"
  }
}
