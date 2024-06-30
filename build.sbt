import org.scalajs.linker.interface.ModuleSplitStyle

ThisBuild / scalaVersion := "3.3.1"
ThisBuild / organization := "myapp"

lazy val myapp = project.in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .enablePlugins(ScalablyTypedConverterExternalNpmPlugin)
  .settings(
    scalaJSUseMainModuleInitializer := true,

    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
        .withModuleSplitStyle(ModuleSplitStyle.SmallModulesFor(List("myapp")))
    },

    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.4.0",
    libraryDependencies += ("org.scala-js" %%% "scalajs-java-securerandom" % "1.0.0").cross(CrossVersion.for3Use2_13),
    libraryDependencies += "com.raquo" %%% "laminar" % "15.0.1",
    libraryDependencies += "myapp" %%% "myapp-common" % "0.1.0",
    libraryDependencies += "dev.zio" %%% "zio-schema" % "1.1.1",
    libraryDependencies += "dev.zio" %%% "zio-schema-json" % "1.1.1",

    externalNpm := baseDirectory.value,
  )
