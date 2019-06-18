/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.radiology.fhir.procedurerequest;

import java.util.Date;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Person;
import org.openmrs.Provider;
import org.openmrs.BaseOpenmrsData;

/**
 * ProcedureRequest is a record of a request for a procedure to be planned, proposed, or performed
 * with or on a patient
 */
public class ProcedureRequest extends BaseOpenmrsData {


    private Integer requestId;

    private String identifier;

    private Status status;

    private Intent intent;

    private Priority priority;

    private Boolean doNotPerform;

    private Concept category;

    private Concept code;

    private Person subject;

    private Encounter context;

    private Date authoredOn;

    private Provider requester;

    private Concept reasonCode;

    private String note;

    public ProcedureRequest() {

    }

    /**
     * Get requestId of ProcedureRequest
     *
     * @return requestId of ProcedureRequest
     */
    @Override
    public Integer getId() {
        return getRequestId();
    }

    /**
     * Set requestId of ProcedureRequest
     *
     * @param requestId of ProcedureRequest
     */
    @Override
    public void setId(Integer requestId) {

        setRequestId(requestId);
    }

    /**
     * Get requestId of ProcedureRequest
     *
     * @return requestId of ProcedureRequest
     */
    public Integer getRequestId() {

        return requestId;
    }

    /**
     * Set requestId of ProcedureRequest
     *
     * @param requestId of ProcedureRequest
     */
    public void setRequestId(Integer requestId) {

        this.requestId = requestId;
    }

    /**
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @param identifier to be set
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Gets the Status
     *
     * @return the Status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status of enum type to be set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return the Intent of the Request
     */
    public Intent getIntent() {
        return intent;
    }

    /**
     * Sets the Intent of the request
     *
     * @param intent to be set
     */
    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    /**
     * Gets the Priority
     *
     * @return the Priority
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Sets the Priority
     *
     * @param priority to be set
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * @return true if Procedure is to be performed
     */
    public Boolean getDoNotPerform() {
        return doNotPerform;
    }

    /**
     * Sets if the Procedure is to be performed or not
     *
     * @param doNotPerform
     */
    public void setDoNotPerform(Boolean doNotPerform) {
        this.doNotPerform = doNotPerform;
    }

    /**
     * @return category of the Procedure
     */
    public Concept getCategory() {
        return category;
    }

    /**
     * @param category to be set
     */
    public void setCategory(Concept category) {
        this.category = category;
    }

    /**
     * @return code
     */
    public Concept getCode() {
        return code;
    }

    /**
     * @param code to be set
     */
    public void setCode(Concept code) {
        this.code = code;
    }

    /**
     * Gets Person on which Procedure is to be performed
     *
     * @return Person
     */
    public Person getSubject() {
        return subject;
    }

    /**
     * Sets Person on which Procedure is to be performed
     * @param subject
     */
    public void setSubject(Person subject) {
        this.subject = subject;
    }

    /**
     * @return context in which request is made
     */
    public Encounter getContext() {
        return context;
    }

    /**
     * Sets the context in which request is made
     *
     * @param context to be set
     */
    public void setContext(Encounter context) {
        this.context = context;
    }

    /**
     * @return Date request was actionable
     */
    public Date getAuthoredOn() {
        return authoredOn;
    }

    /**
     * @param authoredOn Date to be set
     */
    public void setAuthoredOn(Date authoredOn) {
        this.authoredOn = authoredOn;
    }

    /**
     * Gets the Provider who initiates request
     * @return requester
     */
    public Provider getRequester() {
        return requester;
    }

    /**
     * Sets Provider who initiated request
     * @param requester
     */
    public void setRequester(Provider requester) {
        this.requester = requester;
    }

    /**
     * Gets Reason Code
     *
     * @return reasonCode
     */
    public Concept getReasonCode() {
        return reasonCode;
    }

    /**
     * Sets the Reason Code
     *
     * @param reasonCode to be set
     */
    public void setReasonCode(Concept reasonCode) {
        this.reasonCode = reasonCode;
    }

    /**
     * Gets any Notes made about request
     *
     * @return Sting note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note to be set
     */
    public void setNote(String note) {
        this.note = note;
    }

    public enum Status {
        DRAFT,
        ACTIVE,
        SUSPENDED,
        COMPLETED,
        ENTERED_IN_ERROR,
        CANCELLED
    }

    public enum Intent {
        PROPOSAL,
        PLAN,
        ORDER
    }

    public enum Priority {
        ROUTINE,
        URGENT,
        ASAP,
        STAT
    }

}
