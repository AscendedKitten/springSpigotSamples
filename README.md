# Spring Spigot Samples
1.12 sample plugin with Spring Boot integration via Alan Gnomes' [mcspring-boot](https://github.com/Alan-Gomes/mcspring-boot)

* Custom events to monitor players leaving/entering an area defined in a  [.json file](spigot-track/src/main/resources/territories.json)

```java
    @EventHandler
    public void onPlayerLeaveArea(LeaveAreaEvent e) {
        e.getPlayer().sendTitle(ChatColor.RED + e.getTerritory().getName(), "Goodbye!", 10, 20, 10);
    }
 ```
 
 ![Leaving area in-game](https://i.imgur.com/e9yUwso.png)

* Endpoint to query player location stored in-memory using h2; setup for theoretical features
![Web endpoint](https://i.imgur.com/mIY4yDb.png)

* Implementation of reactive paradigm and functional web framework using handler & routes

> Overhead is still huge, please do not use this in any production environment ever