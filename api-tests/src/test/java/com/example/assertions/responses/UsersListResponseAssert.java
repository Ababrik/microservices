package com.example.assertions.responses;

import com.example.responses.Embedded;
import com.example.responses.Links;
import com.example.responses.Page;
import com.example.responses.UsersListResponse;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.util.Objects;

/**
 * {@link UsersListResponse} specific assertions - Generated by CustomAssertionGenerator.
 */
public class UsersListResponseAssert extends AbstractAssert<UsersListResponseAssert, UsersListResponse> {

  /**
   * Creates a new <code>{@link UsersListResponseAssert}</code> to make assertions on actual UsersListResponse.
   * @param actual the UsersListResponse we want to make assertions on.
   */
  public UsersListResponseAssert(UsersListResponse actual) {
    super(actual, UsersListResponseAssert.class);
  }

  /**
   * An entry point for UsersListResponseAssert to follow AssertJ standard <code>assertThat()</code> statements.<br>
   * With a static import, one can write directly: <code>assertThat(myUsersListResponse)</code> and get specific assertion with code completion.
   * @param actual the UsersListResponse we want to make assertions on.
   * @return a new <code>{@link UsersListResponseAssert}</code>
   */
  public static UsersListResponseAssert assertThat(UsersListResponse actual) {
    return new UsersListResponseAssert(actual);
  }

  /**
   * Verifies that the actual UsersListResponse's embedded is equal to the given one.
   * @param embedded the given embedded to compare the actual UsersListResponse's embedded to.
   * @return this assertion object.
   * @throws AssertionError - if the actual UsersListResponse's embedded is not equal to the given one.
   */
  public UsersListResponseAssert hasEmbedded(Embedded embedded) {
    // check that actual UsersListResponse we want to make assertions on is not null.
    isNotNull();

    // overrides the default error message with a more explicit one
    String assertjErrorMessage = "\nExpecting embedded of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";
    
    // null safe check
    Embedded actualEmbedded = actual.getEmbedded();
    if (!Objects.areEqual(actualEmbedded, embedded)) {
      failWithMessage(assertjErrorMessage, actual, embedded, actualEmbedded);
    }

    // return the current assertion for method chaining
    return this;
  }

  /**
   * Verifies that the actual UsersListResponse's links is equal to the given one.
   * @param links the given links to compare the actual UsersListResponse's links to.
   * @return this assertion object.
   * @throws AssertionError - if the actual UsersListResponse's links is not equal to the given one.
   */
  public UsersListResponseAssert hasLinks(Links links) {
    // check that actual UsersListResponse we want to make assertions on is not null.
    isNotNull();

    // overrides the default error message with a more explicit one
    String assertjErrorMessage = "\nExpecting links of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";
    
    // null safe check
    Links actualLinks = actual.getLinks();
    if (!Objects.areEqual(actualLinks, links)) {
      failWithMessage(assertjErrorMessage, actual, links, actualLinks);
    }

    // return the current assertion for method chaining
    return this;
  }

  /**
   * Verifies that the actual UsersListResponse's page is equal to the given one.
   * @param page the given page to compare the actual UsersListResponse's page to.
   * @return this assertion object.
   * @throws AssertionError - if the actual UsersListResponse's page is not equal to the given one.
   */
  public UsersListResponseAssert hasPage(Page page) {
    // check that actual UsersListResponse we want to make assertions on is not null.
    isNotNull();

    // overrides the default error message with a more explicit one
    String assertjErrorMessage = "\nExpecting page of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";
    
    // null safe check
    Page actualPage = actual.getPage();
    if (!Objects.areEqual(actualPage, page)) {
      failWithMessage(assertjErrorMessage, actual, page, actualPage);
    }

    // return the current assertion for method chaining
    return this;
  }

}