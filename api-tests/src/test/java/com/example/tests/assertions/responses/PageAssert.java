package com.example.tests.assertions.responses;

import com.example.responses.Page;
import org.assertj.core.api.AbstractAssert;

/**
 * {@link Page} specific assertions - Generated by CustomAssertionGenerator.
 */
public class PageAssert extends AbstractAssert<PageAssert, Page> {

  /**
   * Creates a new <code>{@link PageAssert}</code> to make assertions on actual Page.
   * @param actual the Page we want to make assertions on.
   */
  public PageAssert(Page actual) {
    super(actual, PageAssert.class);
  }

  /**
   * An entry point for PageAssert to follow AssertJ standard <code>assertThat()</code> statements.<br>
   * With a static import, one can write directly: <code>assertThat(myPage)</code> and get specific assertion with code completion.
   * @param actual the Page we want to make assertions on.
   * @return a new <code>{@link PageAssert}</code>
   */
  public static PageAssert assertThat(Page actual) {
    return new PageAssert(actual);
  }

}
