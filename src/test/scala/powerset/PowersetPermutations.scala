/*
 * Copyright 2011 Bobo Company Ltd.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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
