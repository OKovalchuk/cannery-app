package com.kodynka.app;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.kodynka.app");

        noClasses()
            .that()
            .resideInAnyPackage("com.kodynka.app.service..")
            .or()
            .resideInAnyPackage("com.kodynka.app.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.kodynka.app.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
