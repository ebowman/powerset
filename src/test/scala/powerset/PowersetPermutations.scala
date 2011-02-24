package powerset

import org.scalacheck._
import org.scalacheck.Gen._
import org.scalacheck.Gen
import scala.util.Random

object PowersetPermutations extends Properties("PowersetPermutations") {

  def a: Gen[String] = value("a")

  def b: Gen[String] = value("b")

  def c: Gen[String] = value("c")

  def d: Gen[String] = value("d")

  def e: Gen[String] = value("e")

  val foo = for (s <- choose(1, 5);
                 c <- permute(s, a, b, c, d, e)) yield c.mkString

  def permute[T](n: Int, gs: Gen[T]*): Gen[Seq[T]] = {
    val perm = Random.shuffle(gs.toList)
    for {
      is <- pick(n, 0 until gs.size)
      xs <- sequence[List, T](is.toList.map(perm(_)))
    } yield xs
  }

  implicit def arbString: Arbitrary[String] = Arbitrary(foo)

  property("powerset") = Prop.forAll {
    a: String => println(a); true
  }
}
