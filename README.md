# Two Six Onboarding - Basics

## Overview
This repository is a foundation for an assignment that will familiarize you with some of the tools, frameworks, and development practices that you will work with on the DART project.

### Project Overview
In it's current state, the repository is a skeleton of a basic REST web service written in Scala using the [Scalatra](http://scalatra.org/) framework. Most of the plumbing code to setup the framework is already taken care of for you, therefore you can focus on impelementing the actual logic. The application only consists of a web controller (`src/main/scala/com.twosixlabs.onboarding.ScalatraController`). There are a series of tests (`src/test/scala/com.twosixlabs.onboarding.ScalatraControllerTestSuite`) that will validate some relatively trivial business logic that the controller needs to implement.

The ultimate goal of this excercise is to implement the logic for the controller and then package it as a runnable Docker container for deployment.

## Environment Setup
### Java/Scala Development Environment
1. Ensure you have Java 8 installed (Java 8 is a requirement, it cannot be a newer version of Java)
2. Install [Scala v2.12.8](https://www.scala-lang.org/download/2.12.8.html) and ensure that `scala` is on your system path
3. Install [SBT](https://piccolo.link/sbt-1.2.6.tgz]) and ensure that `sbt` is on your system path
4. Set up and configure your IDE of choice. Eclipse or Intellij IDEA are fine, however, IDEA is preferred

### Docker Environment
1. Make an account at [DockerHub](https://hub.docker.com/).
2. Download the Docker binaries for your platform 
    - [Windows](https://docs.docker.com/docker-for-windows/install/)
    - [Mac OS X](https://docs.docker.com/docker-for-mac/install/)
3. Log into docker on the command line
    ```bash
    docker login -u <docker_user> -p <docker_pass>
    ```
    
### Intellij IDEA
1. Install the Scala plugin
2. Install the SBT plugin
3. Install the Docker integration plugin
4. Restart Intellij IDEA

### Import the project
1. From the Intellij IDEA splash screen, do `Import Project` => `<path_to_project>` => `build.sbt`
2. Import `build.sbt` as a project
3. Review the project settings, ensure that the JDK 8 you installed is the Java runtime
4. Allow the embedded SBT to build the project, there should be no errors.
5. You should be able to run the following command successfully:
    - ```bash
        sbt test:compile
      ```

## Development Instructions
### Building the project for the first time
1. To build the project and run tests you can use the following command (it will take awhile the first time you run it):
    ```$bash
    sbt clean test
    ```
2. Yous should get output that looks similar to this. Don't worry that the tests fail:
    ```$bash
    [info] ScalatraControllerTestSuite:
    [info] GET for `/reverse`
    [info] - should reverse the given string and reply OK (200) *** FAILED ***
    [info]   500 was not equal to 200 (ScalatraControllerTestSuite.scala:13)
    [info] GET for `/reverse`
    [info] - should without a name should return NotFound (404)
    [info] POST for `/summation` for 100
    [info] - should respond OK (200) with the sum of all the numbers 0 - n *** FAILED ***
    [info]   500 was not equal to 200 (ScalatraControllerTestSuite.scala:26)
    [info] POST for `/summation` for a non-numeric value
    [info] - should respond BadRequest (400) *** FAILED ***
    [info]   500 was not equal to 400 (ScalatraControllerTestSuite.scala:33)
    [info] Run completed in 912 milliseconds.
    [info] Total number of tests run: 4
    [info] Suites: completed 1, aborted 0
    [info] Tests: succeeded 1, failed 3, canceled 0, ignored 0, pending 0
    [info] *** 3 TESTS FAILED ***
    [error] Failed tests:
    [error]         com.twosixlabs.onboarding.ScalatraControllerTestSuite
    [error] (Test / test) sbt.TestsFailedException: Tests unsuccessful
    [error] Total time: 1 s, completed Jul 31, 2019 2:20:37 PM
    ```

## Developer Workflow
1. Read and familiarize yourself with the [GitHub flow](https://guides.github.com/introduction/flow/), as we closely follow this model.
2. Create a branch for each task. Each branch name will need to begin with TASK-# where # is the number of the task (ie: for the first task you could). This should be followed by a descriptive branch name. For example: `git checkout -b TASK-1-impelement-controller-logic`
3. Work on your branch however you want, committing changes early and often to keep track of your progress.
4. When you are finished, open a Pull Request for your branch into master.
5. Participate in the review process. Be responsive to comments, and continue to push suggested improvements into your branch.
6. When completed, your changes will be "sqaush" merged into master. The squashed commit message should follow the format "[TASK-#] - a succint description of your changes". To see more about squash merges see [here](https://github.blog/2016-04-01-squash-your-commits/)
7. Re-sync your master branch with the remote master, you will now have your changes squashed into a single commit.
8. Start your next task from the updated master.

## Tasks

### Task 1 - Implement the Controller
1. We like to follow Test Driven Development practices as best we can. Therefore, you will start with the failing tests.
2. Look in `src/test/scala/com.twosixlabs.onboarding.ScalatraControllerTestSuite`. The goal of the first excercise is to get these tests to work.
3. Implement the required behavior in the controller. You can do this any way you want. StackOverflow, Google, or whatever resources you can find to get it working is fine.
4. Feel free to consult the [Scalatra](http://scalatra.org/guides/2.5/) and [ScalaTest](http://www.scalatest.org/user_guide) documentation
5. We are using Scala for a reason. Most of the controller functionality can be achieved very expressively in Scala without having to write tons of code. Do some research on the various functional programming tools available to you such as `map`, `reduce`, `collect`, etc ...

### Task 2 - Containerize the application
1. You can get a deployable artifact by running `sbt assembly`. This will generate a [fat-jar](https://stackoverflow.com/a/29925421), which is our default way of packaging runnable applications.
2. Try running the application on your local machine. You should be able to successfully start the application with the following:
    ```$bash
        java -jar target/scala-2.12/scalatra-exercises-assembly-0.0.1NAPSHOT.jar 
    ```
3. Create a file called `Dockerfile` in the project root directory
4. Your goal is to create a Docker images (which is defined in `Dockerfile`) that runs your fat jar application
5. Some things to consider:
    - You need a base Linux image that can execute Java applications.
    - You need to copy the fat-jar from `target/scala-2.12/scalatra-exercises-assembly-0.0.1NAPSHOT.jar` into the container.
    - You will need a script that starts your application (similar to the command above)
    - You can build the image from scratch yourself (for bonus points), or you can look on [DockerHub](https://hub.docker.com/) for images you can use.
    - Here is the [Dockerfile referencce](https://docs.docker.com/engine/reference/builder/). Feel free to consult any other resources to help you learn more about Docker.
6. In addition to the `Dockerfile`, include a script with the proper commands for building the container. You can use `bash`, `make`, or whatever else as long as it runs on a \*nix environment

## Appendix

### Useful SBT Commands
SBT can be a bit inscrutable to learn at first. While it's similar to Maven, there are some key differences. Here is a helpful guide of common commands for development.
1. Compile the code, no tests
    - `sbt clean compile`
2. Compile the test code, don't execute tests:
    - `sbt clean test:compile`
3. Compile and run tests
    - `sbt clean test`
4. Enable remote debugging for your SBT build (useful for debugging tests)
    - `sbt clean test -jvm-debug $DEBUG_PORT`
5. Run just a single test, you can use a regex to specify the test or pattern of test names
    - `sbt "testOnly **MyTest"`

### Docker Help
- The Docker documentation actually has a decent introduction
    - [Orientation](https://docs.docker.com/get-started/)
    - [Containers](https://docs.docker.com/get-started/part2/)
    - [Services](https://docs.docker.com/get-started/part3/)

### General References and Further Reading
- [Scala exercises](https://www.scala-exercises.org/) is an excellent way to familiarize yourself with Scala language
- [Scala School](https://twitter.github.io/scala_school/) is a knowledge base maintained by Twitter, who is a major Scala user
- [Docker Curriculum](https://docker-curriculum.com/) is a very useful resource for getting started with Docker