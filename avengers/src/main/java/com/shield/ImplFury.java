package com.shield;

import java.util.ArrayList; 
import java.util.List;
import java.util.Scanner;

import com.shield.entity.Avenger;
import com.shield.entity.Mission;
import com.shield.enums.MissionStatus;
import com.shield.enums.NotificationChannel;

public class ImplFury implements Fury{
	
	private static List<Avenger> avengers = new ArrayList<>();
    private static List<Mission> missions = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    
    
    
public static void main(String[] args) throws Exception {
    	
    	try {
    		
    		initializeAvengers();
    		Fury f=new ImplFury();

            while (true) {
                displayMenu();
                int option = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                switch (option) {
                    case 1:
                    	
                    	f.displayMissions();
                    	
                        break;
                    case 2:
                    	f.assignMissionToAvengers();
                        break;
                    case 3:
                    	f.checkMissionDetails();
                        break;
                    case 4:
                    	f.checkAvengerDetails();
                        break;
                    case 5:
                    	f.updateMissionStatus();
                        break;
                    case 6:
                    	f.listAvengers();
                        break;
                    case 7:
                    	f.assignAvengerToMission();
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
			
		} catch (Exception e) {
			System.out.println(e);
		}
        
    }
    
private static void displayMenu() {
    System.out.println("\n=======------S.H.I.E.L.D ------=========\n");
    System.out.println("\nWelcome to Captain Fury. (Note: Below interface is for Captain Fury)");
    System.out.println("1. Check the missions");
    System.out.println("2. Assign mission to Avengers");
    System.out.println("3. Check mission’s details");
    System.out.println("4. Check Avenger’s details");
    System.out.println("5. Update Mission’s status");
    System.out.println("6. List Avengers");
    System.out.println("7. Assign avenger to the mission.");
    System.out.print("Enter the option: ");
}

private static void initializeAvengers() {
    // Initialize avengers with their details
    avengers.add(new Avenger("Iron Man",2, "Tony Stark", "Highly Intelligent Suit of Armor", NotificationChannel.EMAIL));
    avengers.add(new Avenger("Captain America", 2, "Steve Rogers", "Super Soldier", NotificationChannel.SMS));
    avengers.add(new Avenger("Hulk", 2, "Bruce Banner", "Super Strength", NotificationChannel.PAGER));
    avengers.add(new Avenger("Thor", 2, "Thor Odinson", "God of Thunder", NotificationChannel.EMAIL));
    avengers.add(new Avenger("Black Widow", 2, "Natasha Romanoff", "Master Spy", NotificationChannel.SMS));
    avengers.add(new Avenger("Hawkeye", 2, "Clint Barton", "Master Archer", NotificationChannel.PAGER));
}
    
    
    
    
    
    
    
    
    
    
    
    
    

	@Override
	public void displayMissions() {
	    	try {
	    		
	    		 if (missions.isEmpty()) {
	    	            System.out.println("\nNo Mission has been assigned to an Avengers.");
	    	        } else {
	    	            System.out.println("Mission Name | Status | Avengers");
	    	            for (Mission mission : missions) {
	    	                System.out.println(mission.getName() + " | " + mission.getStatus() + " | " + mission.getAssignedAvengers());
	    	            }
	    	        }
	    		
	    	}catch (Exception e) {
				// TODO: handle exception
	    		System.out.println(e);
			}
	       
	}    
			@Override
	    	public void assignMissionToAvengers() {
	    		
	    		try {
	    			
	    			System.out.print("Enter Avengers: ");
	    			String avengersInput = scanner.nextLine();
	    			String[] avengerNames = avengersInput.split(", ");

	    			System.out.print("Enter Mission Name: ");
	    			String missionName = scanner.nextLine();

	    			System.out.print("Enter Mission Details: ");
	    			String missionDetails = scanner.nextLine();

	    			Mission mission = new Mission(missionName, missionDetails);
	    			for (String avengerName : avengerNames) {
	    				avengerName = avengerName.trim();
	    				if (isAvengerAvailable(avengerName)) {
	    					Avenger avenger = getAvengerByName(avengerName);
	    					avenger.assignMission(missionName);
	    					mission.assignAvenger(avengerName);
	    					if(avenger.getLimit()==2) {
	    						avenger.setLimit(1);
	    					}
	    					else if(avenger.getLimit()==1) {
	    						avenger.setLimit(0);
	    					}
	    					System.out.println("Now reaming limit of Avenger:- "+avenger.getLimit());
	    					notifyAvenger(avenger, mission);
	    					
	    					
	    				} else {
	    					System.out.println("\nSorry, " + avengerName + " has already been working on two missions.");
	    					for (String resetlimit : avengerNames) {
	    						Avenger avenger = getAvengerByName(resetlimit);
	    						if(avenger.getLimit()==1) {
		    						avenger.setLimit(2);
		    					}
		    					else if(avenger.getLimit()==0) {
		    						avenger.setLimit(1);
		    					}
	    						System.out.println("Mission got canceled, So "+avenger.getName()+" avenger limit has been reseted");
	    						break;
	    					}
	    					return;
	    				}
	    			}

	    			missions.add(mission);
	    			System.out.println("Mission has been assigned.");
	        		
	        	}
	        	catch (Exception e) {
	    			// TODO: handle exception
	        		System.out.println(e);
	    		}
	    			
	    
	}
			@Override
			public void checkMissionDetails() {
				
				try {
		    		
					System.out.print("Enter Mission Name: ");
					String missionName = scanner.nextLine();

					for (Mission mission : missions) {
						if (mission.getName().equalsIgnoreCase(missionName)) {
							System.out.println("Mission Details: " + mission.getDetails());
							System.out.println("Mission Status: " + mission.getStatus());
							System.out.println("Avengers: " + mission.getAssignedAvengers());
							return;
						}
					}

					System.out.println("Mission not found.");
		    	}
		    	catch (Exception e) {
					// TODO: handle exception
		    		System.out.println(e);
				}
				
			}
			@Override
			public void checkAvengerDetails() {
				
				try {
		    		
					System.out.print("Enter Avenger Name: ");
					String avengerName = scanner.nextLine();

					Avenger avenger = getAvengerByName(avengerName);
					if (avenger != null) {
						System.out.println("Person Name: " + avenger.getPersonName());
						System.out.println("Abilities: " + avenger.getAbilities());
						System.out.println("Mission Assigned: " + avenger.getAssignedMissions().size());
						System.out.println("Mission Completed: " + getCompletedMissionsCount(avenger));
					} else {
						System.out.println("Avenger not found.");
					}
		    	}
		    	catch (Exception e) {
					// TODO: handle exception
		    		System.out.println(e);
				}
				
			}
			@Override
			public void updateMissionStatus() {
				try {
		    		
					
					System.out.print("Enter Mission Name: ");
					String missionName = scanner.nextLine();

					for (Mission mission : missions) {
						if (mission.getName().equalsIgnoreCase(missionName)) {
							System.out.print("Enter new status: ");
							String newStatus = scanner.nextLine();

							if (newStatus.equalsIgnoreCase("Completed")) {
								notifyAvengersOfMissionCompletion(mission);
								mission.completeMission();
								System.out.println("Email and SMS are sent to " + mission.getAssignedAvengers());
								//to get 
								for (String avengerName : mission.getAssignedAvengers()) {
									Avenger avenger = getAvengerByName(avengerName);
									if(avenger.getLimit()==0) {
										avenger.setLimit(1);
									}
									else if(avenger.getLimit()==1) {
										avenger.setLimit(2);
									}
								}
								
							} else {
								System.out.println("Invalid status. Only 'Completed' status can be set.");
							}
							return;
						}
					}

					System.out.println("Mission not found.");
		    	}
		    	catch (Exception e) {
					// TODO: handle exception
		    		System.out.println(e);
				}
				
			}
			@Override
			public void listAvengers() {
				try {
					
					System.out.println("Avenger Name | Status | Assigned Mission");
					for (Avenger avenger : avengers) {
						System.out.println(
								avenger.getName() + "   |  " + getAvengerStatus(avenger) + "   |   " + avenger.getAssignedMissions());
					}
		    		
		    	}
		    	catch (Exception e) {
					// TODO: handle exception
		    		System.out.println(e);
				}
				
			}

			public void assignAvengerToMission() {
				
				try {
					
					System.out.print("Enter Avenger Name: ");
					String avengerName = scanner.nextLine();

					System.out.print("Enter Mission Name: ");
					String missionName = scanner.nextLine();

					for (Mission mission : missions) {
						if (mission.getName().equalsIgnoreCase(missionName)) {
							if (mission.getAssignedAvengers().size() < 2) {
								Avenger avenger = getAvengerByName(avengerName);
								if (avenger != null && isAvengerAvailable(avengerName)) {
									avenger.assignMission(missionName);
									mission.assignAvenger(avengerName);
									if(avenger.getLimit()==2) {
			    						avenger.setLimit(1);
			    					}
			    					else if(avenger.getLimit()==1) {
			    						avenger.setLimit(0);
			    					}
									notifyAvenger(avenger, mission);
									System.out.println("Avenger assigned to the mission.");
								} else {
									System.out.println("Avenger is already working on two missions or not found.");
								}
							} else {
								System.out.println(mission.getAssignedAvengers()+ "avengers already assigned to this mission.");
							}
							return;
						}
					}

					System.out.println("Mission not found.");
		    		
		    	}
		    	catch (Exception e) {
					// TODO: handle exception
		    		System.out.println(e);
				}
				
			}
			
			
	    	
			public static boolean isAvengerAvailable(String avengerName) {
				Avenger avenger = getAvengerByName(avengerName);
				return avenger != null && avenger.getLimit() != 0;
			}

			public static Avenger getAvengerByName(String avengerName) {
				for (Avenger avenger : avengers) {
					if (avenger.getName().equalsIgnoreCase(avengerName)) {
						return avenger;
					}
				}
				return null;
			}

			public static void notifyAvenger(Avenger avenger, Mission mission) {
				// Implement notification logic based on avenger's notification channel
				// This is a placeholder method for demonstration purposes
				System.out.println("Notification sent to " + avenger.getName() + " via " + avenger.getNotificationChannel());
			}

			public static void notifyAvengersOfMissionCompletion(Mission mission) {
				for (String avengerName : mission.getAssignedAvengers()) {
					Avenger avenger = getAvengerByName(avengerName);
					notifyAvenger(avenger, mission);
				}
			}

			private static int getCompletedMissionsCount(Avenger avenger) {
				int completedMissions = 0;
				for (Mission mission : missions) {
					if (mission.getAssignedAvengers().contains(avenger.getName())
							&& mission.getStatus() == MissionStatus.COMPLETED) {
						completedMissions++;
					}
				}
				return completedMissions;
			}

			private static String getAvengerStatus(Avenger avenger) {
				
				return avenger.getLimit()==2 ? "Available" : "On Mission";
			}

}
