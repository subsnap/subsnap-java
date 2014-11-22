/**
 * Copyright © 2014 salesforce.com, inc. All rights reserved.
 */
package co.subsnap.constants;

/**
 * Provides access to resource related constants.
 * 
 * @author ukanani
 * @since 1.0
 */

public class DatabaseConstants {

    /*
     * Hibernate Configuration:
     */

    public final static String ENTITYMANAGER_PACKAGES_TO_SCAN = "com.salesforce.belvedere.domain.jpa";
    
    public static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    public static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    public static final String PROPERTY_NAME_HIBERNATE_DEFAULT_SCHEMA = "hibernate.default_schema";
    public static final String PROPERTY_NAME_HIBERNATE_SET_BIG_STRING ="SetBigStringTryClobtrue";
    
    public final static String COUNTER_USERS = "users";
    public final static String COUNTER_RESERVATIONS = "reservations";
    public final static String COUNTER_RESOURCES = "resources";
    public final static String BELVEDERE_USERS = "users";
    public final static String BELVEDERE_RESERVATIONS = "reservations";
    public final static String BELVEDERE_RESOURCES = "resources";
    public final static String COUNTER = "counters";
    
    public final static String INBOUND_EXPIRE_RESERVATION_CHANNEL = "inboundDatabaseExpiredReservationChannel";
    public final static String INBOUND_DELETE_RESERVATION_CHANNEL = "inboundDatabaseDeleteReservationChannel";
    public final static String INBOUND_EXPIRE_RESERVATION_NOTIFICATION_CHANNEL = "inboundDatabaseExpirationNotificationReservationChannel";
}
