package org.checkerframework.checker.crypto;

import javax.annotation.processing.ProcessingEnvironment;
import org.checkerframework.common.basetype.BaseTypeChecker;
import org.checkerframework.common.basetype.BaseTypeVisitor;

public class CryptoVisitor extends BaseTypeVisitor<CryptoAnnotatedTypeFactory> {

    final boolean STRONG_BOX_BACKED_ENABLE = checker.getLintOption("strongboxbacked", false);

    final ProcessingEnvironment env;

    public CryptoVisitor(final BaseTypeChecker checker) {
        super(checker);
        env = checker.getProcessingEnvironment();
    }
}
