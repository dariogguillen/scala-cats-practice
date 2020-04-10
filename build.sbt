lazy val root = (project in file("."))
  .settings(
    name := "cats-sandbox",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.1",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % "2.0.0",
      "org.typelevel" %% "cats-effect" % "2.0.0",
      "org.scalatest" %% "scalatest" % "3.1.0" % "test"
    ),
    scalacOptions in Compile ++= Seq(
      "-deprecation", // warn about use of deprecated APIs
      "-feature", // warn about misused language features
      "-unchecked", // warn about unchecked type parameters
      "-language:higherKinds" // allow higher kinded types without `import scala.language.higherKinds`
    ),
    addCompilerPlugin(
      "org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full
    )
  )
