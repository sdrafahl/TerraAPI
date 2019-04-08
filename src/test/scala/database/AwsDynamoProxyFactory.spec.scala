package lambdas.database

import org.scalatest._
import org.scalamock.scalatest.{AsyncMockFactory, MockFactory}
import awscala._
import cats.effect.IO
import dynamodbv2._
import org.scalacheck._
import lambdas.database._
import lambdas.config
import lambdas.config.AWSConfig
import lambdas.config
import lambdas.config.GlobalConfigs.AWSConfig

class AwsDynamoProxyFactoryTest extends FunSpec with Matchers with MockFactory {

  describe("AwsDynamoProxyFactory") {
      it("should have a implicit factory") {
          val proxyFactory = implicitly[AwsDynamoProxyFactory]
          assert(proxyFactory.isInstanceOf[AwsDynamoProxyFactory])
      }
      it("should return a AwsDynamoProxy") {
        val accessKey = Gen.alphaNumChar.toString
        val secreteAccessKey = Gen.alphaNumChar.toString
        val testRegion = Gen.alphaNumChar.toString
        val testUserTable = Gen.alphaNumChar.toString
        
        val awsConfig: AWSConfig = new AWSConfig(accessKey, secreteAccessKey, testRegion)
        val testFactory = implicitly[AwsDynamoProxyFactory]
        val returnedAwsDynamoProxy = testFactory(testUserTable)(awsConfig)
        val correctAwsDynamoProxy = new AwsDynamoProxy(new AwsAccessKeys(awsConfig), testUserTable)
        println(returnedAwsDynamoProxy.toString)
        println(correctAwsDynamoProxy.toString)
        assert(returnedAwsDynamoProxy.equals(correctAwsDynamoProxy))
      }
  }
}