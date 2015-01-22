package com.danshannon.strava.api.service;

import java.util.List;

import com.danshannon.strava.api.model.Activity;
import com.danshannon.strava.api.model.Athlete;
import com.danshannon.strava.api.model.Club;
import com.danshannon.strava.api.model.reference.ResourceState;
import com.danshannon.strava.api.service.exception.NotFoundException;
import com.danshannon.strava.api.service.exception.UnauthorizedException;
import com.danshannon.strava.util.Paging;

/**
 * <p>{@link Club} related services</p>
 * 
 * <p>{@link Club Clubs} represent groups of {@link Athlete athletes} on Strava. They can be public or private.</p>
 * 
 * <p>Only members of private clubs can access their details.</p>
 * 
 * <p>The object is returned in summary or detailed {@link ResourceState representations}.</p>
 * 
 * @author Dan Shannon
 *
 */
public interface ClubServices {
	/**
	 * <p>Retrieve details about a specific {@link Club club}. The club must be public or the current {@link Athlete athlete} must be a member.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/clubs/:id</p>
	 * 
	 * <p>Returns <code>null</code> if club with the given id does not exist</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-details>http://strava.github.io/api/v3/clubs/#get-details</a>
	 * 
	 * @param id The id of the {@link Club} to be retrieved
	 * @return Returns a detailed club representation.
	 */
	public Club getClub(Integer id) throws NotFoundException;
	
	/**
	 * <p>Fetch an array of {@link Club clubs} that the currently authenticated {@link Athlete athlete} is a member of.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athlete/clubs
	 * 
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-athletes">http://strava.github.io/api/v3/clubs/#get-athletes</a>
	 * 
	 * @return Returns a {@link Club club} summary {@link ResourceState representation}.
	 */
	public List<Club> listAuthenticatedAthleteClubs() throws UnauthorizedException;
	
	/**
	 * <p>Retrieve summary information about member {@link Athlete athletes} of a specific {@link Club club}.</p>
	 * 
	 * <p>Pagination is not supported.</p>
	 * 
	 * <p>Returns <code>null</code> if club with the given id does not exist</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/clubs/:id/members</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-members">http://strava.github.io/api/v3/clubs/#get-members</a>
	 * 
	 * @param id The id of the {@link Club} whose member {@link Athlete athletes} should be returned
	 * @return Returns an array of {@link Athlete athlete} summary {@link ResourceState representations}.
	 * @throws NotFoundException If club does not exist
	 * @throws UnauthorizedException If club is private
	 */
	public List<Athlete> listClubMembers(Integer id) throws NotFoundException, UnauthorizedException;
	
	/**
	 * <p>Retrieve summary information about member {@link Athlete athletes} of a specific {@link Club club}.</p>
	 * 
	 * <p>Pagination is supported.</p>
	 * 
	 * <p>Returns <code>null</code> if club with the given id does not exist</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/clubs/:id/members</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-members">http://strava.github.io/api/v3/clubs/#get-members</a>
	 * 
	 * @param id The id of the {@link Club} whose member {@link Athlete athletes} should be returned
	 * @param pagingInstruction (Optional) The page to be returned
	 * @return Returns an array of {@link Athlete athlete} summary {@link ResourceState representations}.
	 * @throws NotFoundException If club does not exist
	 * @throws UnauthorizedException If club is private
	 */
	public List<Athlete> listClubMembers(Integer id, Paging pagingInstruction) throws NotFoundException, UnauthorizedException;
	
	/**
	 * <p>Retrieve the recent {@link Activity activities} performed by member {@link Athlete athletes} of a specific {@link Club club}.</p>
	 * 
	 * <p>The authenticated athlete must be a member of the club.</p>
	 * 
	 * <p>Returns <code>null</code> if club with the given id does not exist</p>
	 * 
	 * <p>Pagination is NOT supported.</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-activities">http://strava.github.io/api/v3/clubs/#get-activities</a>
	 * 
	 * @param id The id of the {@link Club} for which recent {@link Activity activities} are to be returned.
	 * @param page (Optional) Page to start at for pagination
	 * @param perPage (Optional) Number of results per page (max 200)
	 * @return Returns an array of {@link Activity activity} summary {@link ResourceState representations}.
	 * @throws NotFoundException If club does not exist
	 * @throws UnauthorizedException If club is private
	 */
	public List<Activity> listRecentClubActivities(Integer id) throws NotFoundException, UnauthorizedException;
	
	/**
	 * <p>Retrieve the recent {@link Activity activities} performed by member {@link Athlete athletes} of a specific {@link Club club}.</p>
	 * 
	 * <p>The authenticated athlete must be a member of the club.</p>
	 * 
	 * <p>Returns <code>null</code> if club with the given id does not exist</p>
	 * 
	 * <p>Pagination is supported. However, the results are limited to the last 200 total activities by club members.</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/clubs/#get-activities">http://strava.github.io/api/v3/clubs/#get-activities</a>
	 * 
	 * @param id The id of the {@link Club} for which recent {@link Activity activities} are to be returned.
	 * @param page (Optional) Page to start at for pagination
	 * @param perPage (Optional) Number of results per page (max 200)
	 * @return Returns an array of {@link Activity activity} summary {@link ResourceState representations}.
	 * @throws NotFoundException If club does not exist
	 * @throws UnauthorizedException If club is private
	 */
	public List<Activity> listRecentClubActivities(Integer id, Paging pagingInstruction) throws NotFoundException, UnauthorizedException;
	
	/**
	 * <p>Join a club on behalf of the authenticated user. An access token with write permissions is required.</p>
	 * 
	 * @param id ID of the {@link Club} to join
	 * @throws NotFoundException If the club does not exist
	 * @throws UnauthorizedException If the user does not have a token with write permission
	 */
	public void joinClub(Integer id) throws NotFoundException, UnauthorizedException;
	
	/**
	 * <p>Leave a club on behalf of the authenticated user. An access token with write permissions is required.</p>
	 * 
	 * @param id ID of the club to join
	 * @throws NotFoundException If the club does not exist
	 * @throws UnauthorizedException If the user does not have a token with write permission
	 */
	public void leaveClub(Integer id) throws NotFoundException, UnauthorizedException;
}
