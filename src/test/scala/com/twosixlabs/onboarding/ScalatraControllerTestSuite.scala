package com.twosixlabs.onboarding

import org.scalatest.FlatSpecLike
import org.scalatra.test.scalatest.ScalatraSuite

class ScalatraControllerTestSuite extends FlatSpecLike with ScalatraSuite {

    //@formatter:off
    addServlet( new ScalatraController, "/*" )

    "GET for `/reverse`" should "reverse the given string and reply OK (200)" in {
        get( "/reverse/foobar" ) {
            status shouldBe 200
            body shouldBe "raboof"
        }
    }

    "GET for `/reverse`" should "without a name should return NotFound (404)" in {
        get( "/reverse" ) {
            status shouldBe 404
        }
    }

    "POST for `/summation` for 100" should "respond OK (200) with the sum of all the numbers 0 - n" in {
        post( "/summation", ( "n" -> "100" ) ) {
            status shouldBe 200
            body.toInt shouldBe 5050
        }
    }

    "POST for `/summation` for a non-numeric value" should "respond BadRequest (400)" in {
        post( "/summation", ( "n" -> "abc" ) ) {
           status shouldBe 400
       }
    }
    //@formatter:off
}
