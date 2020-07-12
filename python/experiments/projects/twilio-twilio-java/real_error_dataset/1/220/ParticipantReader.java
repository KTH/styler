/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.conference;

import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class ParticipantReader extends Reader<Participant> {
    private String pathAccountSid;
    private final String pathConferenceSid;
    private Boolean muted;
    private Boolean hold;
    private Boolean coaching;

    /**
     * Construct a new ParticipantReader.
     *
     * @param pathConferenceSid The SID of the conference with the participants to
     *                          read
     */
    public ParticipantReader(final String pathConferenceSid) {
        this.pathConferenceSid = pathConferenceSid;
    }

    /**
     * Construct a new ParticipantReader.
     *
     * @param pathAccountSid The SID of the Account that created the resources to
     *                       read
     * @param pathConferenceSid The SID of the conference with the participants to
     *                          read
     */
    public ParticipantReader(final String pathAccountSid,
                             final String pathConferenceSid) {
        this.pathAccountSid = pathAccountSid;
        this.pathConferenceSid = pathConferenceSid;
    }

    /**
     * Whether to return only participants that are muted. Can be: `true` or
     * `false`..
     *
     * @param muted Whether to return only participants that are muted
     * @return this
     */
    public ParticipantReader setMuted(final Boolean muted) {
        this.muted = muted;
        return this;
    }

    /**
     * Whether to return only participants that are on hold. Can be: `true` or
     * `false`..
     *
     * @param hold Whether to return only participants that are on hold
     * @return this
     */
    public ParticipantReader setHold(final Boolean hold) {
        this.hold = hold;
        return this;
    }

    /**
     * Whether to return only participants who are coaching another call. Can be:
     * `true` or `false`..
     *
     * @param coaching Whether to return only participants who are coaching another
     *                 call
     * @return this
     */
    public ParticipantReader setCoaching(final Boolean coaching) {
        this.coaching = coaching;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Participant ResourceSet
     */
    @Override
    public ResourceSet<Participant> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Participant ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Participant> firstPage(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.GET,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/Conferences/" + this.pathConferenceSid + "/Participants.json"
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the target page from the Twilio API.
     *
     * @param targetUrl API-generated URL for the requested results page
     * @param client TwilioRestClient with which to make the request
     * @return Participant ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Participant> getPage(final String targetUrl, final TwilioRestClient client) {
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
    public Page<Participant> nextPage(final Page<Participant> page,
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
    public Page<Participant> previousPage(final Page<Participant> page,
                                          final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(Domains.API.toString())
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Participant Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Participant> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Participant read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
           throw new ApiException(restException);
        }

        return Page.fromJson(
            "participants",
            response.getContent(),
            Participant.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (muted != null) {
            request.addQueryParam("Muted", muted.toString());
        }

        if (hold != null) {
            request.addQueryParam("Hold", hold.toString());
        }

        if (coaching != null) {
            request.addQueryParam("Coaching", coaching.toString());
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}