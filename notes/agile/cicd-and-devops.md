1. CI (Continuous Integration)
2. CD (Continuous Delivery / Continuous Deployment)
3. SRE: Site Reliability Engineering

Stages of the CI/CD pipeline
Continuous integration -> continuous delivery -> continuous deployment


Continuous integration:
`Continuous integration` (CI) refers to the practice of automatically and frequently integrating code changes into a shared source code repository.

Continuous delivery and deployment:
Continuous delivery stops short of automatic production deployment, while continuous deployment automatically releases the updates into the production environment.
Continuous delivery automates the release of validated code to a repository following the automation of builds and unit and integration testing in CI.
Continuous deployment is an extension of continuous delivery, and can refer to automating the release of a developer’s changes from the repository to production, where it is usable by customers.

Deployment strategies
1. Canary deployment: Roll out to subset of users first
2. Blue-green deployment: Blue version has the older version and green has the new version so if there's any problem we can switch back to the blue version

GitHub Actions
1. Workflows: a YAML file.
2. Events: `push`, `pull_request`, `schedule`
3. Jobs: A workflow is made up of one or more jobs
4. Steps: Each job contains steps
5. Runners: Servers that actually run the code
