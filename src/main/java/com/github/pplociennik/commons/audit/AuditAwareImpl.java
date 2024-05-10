/*
 * <!--
 *   ~ MIT License
 *   ~
 *   ~ Copyright (c) 2024 Przemysław Płóciennik
 *   ~
 *   ~ Permission is hereby granted, free of charge, to any person obtaining a copy
 *   ~ of this software and associated documentation files (the "Software"), to deal
 *   ~ in the Software without restriction, including without limitation the rights
 *   ~ to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   ~ copies of the Software, and to permit persons to whom the Software is
 *   ~ furnished to do so, subject to the following conditions:
 *   ~
 *   ~ The above copyright notice and this permission notice shall be included in all
 *   ~ copies or substantial portions of the Software.
 *   ~
 *   ~ THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   ~ IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   ~ FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   ~ AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   ~ LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   ~ OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   ~ SOFTWARE.
 *   -->
 */

package com.github.pplociennik.commons.audit;

import org.springframework.data.domain.AuditorAware;

import java.time.Instant;
import java.util.Optional;

/**
 * A default auditor. Needs to be configured in the spring application starter using the @EnableJpaAuditing annotation.
 *
 * @author Created by: Pplociennik at 10.03.2024 14:27
 */
public class AuditAwareImpl implements AuditorAware< Instant > {

    @Override
    public Optional< Instant > getCurrentAuditor() {
        return Optional.of( Instant.now() );
    }
}
