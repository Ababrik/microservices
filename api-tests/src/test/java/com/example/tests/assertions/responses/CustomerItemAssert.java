package com.example.tests.assertions.responses;

import com.example.responses.CustomerItem;
import com.example.responses.Links;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.util.Objects;

/**
 * {@link CustomerItem} specific assertions - Generated by CustomAssertionGenerator.
 */
public class CustomerItemAssert extends AbstractAssert<CustomerItemAssert, CustomerItem> {

  /**
   * Creates a new <code>{@link CustomerItemAssert}</code> to make assertions on actual CustomerItem.
   * @param actual the CustomerItem we want to make assertions on.
   */
  public CustomerItemAssert(CustomerItem actual) {
    super(actual, CustomerItemAssert.class);
  }

  /**
   * An entry point for CustomerItemAssert to follow AssertJ standard <code>assertThat()</code> statements.<br>
   * With a static import, one can write directly: <code>assertThat(myCustomerItem)</code> and get specific assertion with code completion.
   * @param actual the CustomerItem we want to make assertions on.
   * @return a new <code>{@link CustomerItemAssert}</code>
   */
  public static CustomerItemAssert assertThat(CustomerItem actual) {
    return new CustomerItemAssert(actual);
  }

  /**
   * Verifies that the actual CustomerItem's firstName is equal to the given one.
   * @param firstName the given firstName to compare the actual CustomerItem's firstName to.
   * @return this assertion object.
   * @throws AssertionError - if the actual CustomerItem's firstName is not equal to the given one.
   */
  public CustomerItemAssert hasFirstName(String firstName) {
    // check that actual CustomerItem we want to make assertions on is not null.
    isNotNull();

    // overrides the default error message with a more explicit one
    String assertjErrorMessage = "\nExpecting firstName of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";
    
    // null safe check
    String actualFirstName = actual.getFirstName();
    if (!Objects.areEqual(actualFirstName, firstName)) {
      failWithMessage(assertjErrorMessage, actual, firstName, actualFirstName);
    }

    // return the current assertion for method chaining
    return this;
  }

  /**
   * Verifies that the actual CustomerItem's id is equal to the given one.
   * @param id the given id to compare the actual CustomerItem's id to.
   * @return this assertion object.
   * @throws AssertionError - if the actual CustomerItem's id is not equal to the given one.
   */
  public CustomerItemAssert hasId(String id) {
    // check that actual CustomerItem we want to make assertions on is not null.
    isNotNull();

    // overrides the default error message with a more explicit one
    String assertjErrorMessage = "\nExpecting id of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";
    
    // null safe check
    String actualId = actual.getId();
    if (!Objects.areEqual(actualId, id)) {
      failWithMessage(assertjErrorMessage, actual, id, actualId);
    }

    // return the current assertion for method chaining
    return this;
  }

  /**
   * Verifies that the actual CustomerItem's lastName is equal to the given one.
   * @param lastName the given lastName to compare the actual CustomerItem's lastName to.
   * @return this assertion object.
   * @throws AssertionError - if the actual CustomerItem's lastName is not equal to the given one.
   */
  public CustomerItemAssert hasLastName(String lastName) {
    // check that actual CustomerItem we want to make assertions on is not null.
    isNotNull();

    // overrides the default error message with a more explicit one
    String assertjErrorMessage = "\nExpecting lastName of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";
    
    // null safe check
    String actualLastName = actual.getLastName();
    if (!Objects.areEqual(actualLastName, lastName)) {
      failWithMessage(assertjErrorMessage, actual, lastName, actualLastName);
    }

    // return the current assertion for method chaining
    return this;
  }

  /**
   * Verifies that the actual CustomerItem's links is equal to the given one.
   * @param links the given links to compare the actual CustomerItem's links to.
   * @return this assertion object.
   * @throws AssertionError - if the actual CustomerItem's links is not equal to the given one.
   */
  public CustomerItemAssert hasLinks(Links links) {
    // check that actual CustomerItem we want to make assertions on is not null.
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
   * Verifies that the actual CustomerItem's username is equal to the given one.
   * @param username the given username to compare the actual CustomerItem's username to.
   * @return this assertion object.
   * @throws AssertionError - if the actual CustomerItem's username is not equal to the given one.
   */
  public CustomerItemAssert hasUsername(String username) {
    // check that actual CustomerItem we want to make assertions on is not null.
    isNotNull();

    // overrides the default error message with a more explicit one
    String assertjErrorMessage = "\nExpecting username of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";
    
    // null safe check
    String actualUsername = actual.getUsername();
    if (!Objects.areEqual(actualUsername, username)) {
      failWithMessage(assertjErrorMessage, actual, username, actualUsername);
    }

    // return the current assertion for method chaining
    return this;
  }

}
