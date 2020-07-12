/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account;

import com.google.common.collect.Range;
import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import org.joda.time.LocalDate;

public class ConferenceReader extends Reader<Conference> {
    private String pathAccountSid;
    private LocalDate absoluteDateCreated;
    private Range<LocalDate> rangeDateCreated;
    private LocalDate absoluteDateUpdated;
    private Range<LocalDate> rangeDateUpdated;
    private String friendlyName;
    private Conference.Status status;

    /**
     * Construct a new ConferenceReader.
     */
    public ConferenceReader() {
    }

    /**
     * Construct a new ConferenceReader.
     *
     * @param pathAccountSid The SID of the Account that created the resource(s) to
     *                       read
     */
    public ConferenceReader(final String pathAccountSid) {
        this.pathAccountSid = pathAccountSid;
    }

    /**
     * The `date_created` value, specified as `YYYY-MM-DD`, of the resources to
     * read. To read conferences that started on or before midnight on a date, use
     * `&lt;=YYYY-MM-DD`, and to specify  conferences that started on or after
     * midnight on a date, use `&gt;=YYYY-MM-DD`..
     *
     * @param absoluteDateCreated The `YYYY-MM-DD` value of the resources to read
     * @return this
     */
    public ConferenceReader setDateCreated(final LocalDate absoluteDateCreated) {
        this.rangeDateCreated = null;
        this.absoluteDateCreated = absoluteDateCreated;
        return this;
    }

    /**
     * The `date_created` value, specified as `YYYY-MM-DD`, of the resources to
     * read. To read conferences that started on or before midnight on a date, use
     * `&lt;=YYYY-MM-DD`, and to specify  conferences that started on or after
     * midnight on a date, use `&gt;=YYYY-MM-DD`..
     *
     * @param rangeDateCreated The `YYYY-MM-DD` value of the resources to read
     * @return this
     */
    public ConferenceReader setDateCreated(final Range<LocalDate> rangeDateCreated) {
        this.absoluteDateCreated = null;
        this.rangeDateCreated = rangeDateCreated;
        return this;
    }

    /**
     * The `date_updated` value, specified as `YYYY-MM-DD`, of the resources to
     * read. To read conferences that were last updated on or before midnight on a
     * date, use `&lt;=YYYY-MM-DD`, and to specify conferences that were last
     * updated on or after midnight on a given date, use  `&gt;=YYYY-MM-DD`..
     *
     * @param absoluteDateUpdated The `YYYY-MM-DD` value of the resources to read
     * @return this
     */
    public ConferenceReader setDateUpdated(final LocalDate absoluteDateUpdated) {
        this.rangeDateUpdated = null;
        this.absoluteDateUpdated = absoluteDateUpdated;
        return this;
    }

    /**
     * The `date_updated` value, specified as `YYYY-MM-DD`, of the resources to
     * read. To read conferences that were last updated on or before midnight on a
     * date, use `&lt;=YYYY-MM-DD`, and to specify conferences that were last
     * updated on or after midnight on a given date, use  `&gt;=YYYY-MM-DD`..
     *
     * @param rangeDateUpdated The `YYYY-MM-DD` value of the resources to read
     * @return this
     */
    public ConferenceReader setDateUpdated(final Range<LocalDate> rangeDateUpdated) {
        this.absoluteDateUpdated = null;
        this.rangeDateUpdated = rangeDateUpdated;
        return this;
    }

    /**
     * The string that identifies the Conference resources to read..
     *
     * @param friendlyName The string that identifies the Conference resources to
     *                     read
     * @return this
     */
    public ConferenceReader setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The status of the resources to read. Can be: `init`, `in-progress`, or
     * `completed`..
     *
     * @param status The status of the resources to read
     * @return this
     */
    public ConferenceReader setStatus(final Conference.Status status) {
        this.status = status;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Conference ResourceSet
     */
    @Override
    public ResourceSet<Conference> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Conference ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Conference> firstPage(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.GET,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/Conferences.json"
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the target page from the Twilio API.
     *
     * @param targetUrl API-generated URL for the requested results page
     * @param client TwilioRestClient with which to make the request
     * @return Conference ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Conference> getPage(final String targetUrl, final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.GET,
            targetUrl
        );

        return pageForRequest(client, request);
    }

    /**
     * Retrieve the next page from the Twilio API.
     *
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Next Page
     */
    @Override
    public Page<Conference> nextPage(final Page<Conference> page,
                                     final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(Domains.API.toString())
        );
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the previous page from the Twilio API.
     *
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Previous Page
     */
    @Override
    public Page<Conference> previousPage(final Page<Conference> page,
                                         final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.API.toString())
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Conference Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Conference> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Conference read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
           throw new ApiException(restException);
        }

        return Page.fromJson(
            "conferences",
            response.getContent(),
            Conference.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (absoluteDateCreated != null) {
            request.addQueryParam("DateCreated", absoluteDateCreated.toString(Request.QUERY_STRING_DATE_FORMAT));
        } else if (rangeDateCreated != null) {
            request.addQueryDateRange("DateCreated", rangeDateCreated);
        }

        if (absoluteDateUpdated != null) {
            request.addQueryParam("DateUpdated", absoluteDateUpdated.toString(Request.QUERY_STRING_DATE_FORMAT));
        } else if (rangeDateUpdated != null) {
            request.addQueryDateRange("DateUpdated", rangeDateUpdated);
        }

        if (friendlyName != null) {
            request.addQueryParam("FriendlyName", friendlyName);
        }

        if (status != null) {
            request.addQueryParam("Status", status.toString());
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}