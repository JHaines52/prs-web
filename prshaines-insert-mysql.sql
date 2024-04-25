USE prsjhaines;

INSERT INTO User (Username, Password, Firstname, LastName, Phone, Email, Reviewer, Admin, ProfilePhoto)
VALUES ('YurlunggurSerpent', 'Scale123', 'Prismara', 'Yurlunggur', '111-999-8888', 'RainbowSerpent@dreamtime.au', TRUE, FALSE, 'assets/images/rainbow.jpg'),
('EvaEmpimeliad', 'trees45', 'Eldora', 'Empimeliad', '202-555-1234', 'woodnymph@greekmythology.gr', FALSE, FALSE, 'assets/images/nymph.jpg'),
('RadagastBrown', 'Wizard3', 'Radagast', 'TheBrown', '000-234-5678', 'wizard@middleearth.net', TRUE, FALSE, 'assets/images/radagast.jpg'),
('SmaugTheGolden', 'Dragon8', 'Smaug', 'TheGolden', '999-999-0000', 'firedragon@middleearth.net', TRUE, TRUE, 'assets/images/smaug.jpg'),
('TitanusGojira', 'Monster2', 'Godzilla', 'Daikaijus', '555-555-0505', 'titanus@hollowearth.mon', FALSE, FALSE, 'assets/images/godzilla.jpg'),
('WorldSerpent', 'Monster4', 'Jörmungandr', 'Midgard Serpent', '233-312-6789', 'jormungandr@midgard.sea', TRUE, FALSE, 'assests/images/Jormungandr.jpg'),
('LinkHero', 'Boy123', 'Link', 'Link', '111-777-0123', 'link@thelegendofzelda.com', FALSE, FALSE, 'assets/images/link.jpg'),
('DarkLord', 'Horcrux13', 'Tom', 'Riddle', '000-666-0000', 'voldemort@harrypotter.com', FALSE, TRUE, 'assets/images/voldemort.jpg'),
('mando', 'mandalor', 'Din', 'Djarin', '123-456-7890', 'mando@starwars.com', false, false, 'assets/images/mandalorian.jpg'),
('powerfulMaiar', 'Wizard1', 'Annatar', 'Sauron', '123-456-7890', 'annatar@middleearth.net', false, true, 'assets/images/sauron.jpg'),
('Anubis', 'EgyptGod1', 'Anubis', 'UnderworldGuide', '000-000-0000', 'anubis@acientegypt.eg', false, true, 'assets/images/anubis.jpg'),
('Sasquatch', 'HidAndSeek', 'Sasquatch', 'Bigfoot', '675-565-5835', 'sasquatch@mythicalcreatures.com', false, false, 'assets/images/sasquatch.jpg'),
('KraotsStrength', 'GreekGod1', 'Kraots', 'son of Pallas', '123-456-7890', 'kraots@greekmythology.gr', false, true, 'assets/images/kratos.jpg'),
('KyloDarkForce', 'password', 'Kylo', 'Ren', '232-788-0056', 'darkwarrior@starwars.com', false, false, 'assets/images/kylo.jpg');



-- vendor data
INSERT INTO Vendor (Code, Name, Address, City, Region, Zip, Phone, Email)
VALUES
('RF001', 'Rohirrim Farmer', 'The Great Plains', 'Edoras', 'Rohan', '32045', '123-456-7890', 'farmer@rohan.me'),
('BYB001', 'Boneyard Beer', '1234 Brew St', 'Bend', 'Oregon', '97703', '541-555-1234', 'info@boneyardbeer.com'),
('LH2001', 'Life H20', '123 Water St', 'Darwin', 'Australia', '08200', '008-124-5678', 'info@lifeh20.com.au'),
('PA100', 'Peristeri Attica', '123 Olive Lane', 'Athens', 'Greece', '10431', '030-210-1234', 'contact@peristeriattica.gr'),
('BB001', 'Borgin and Burkes', '13B Knockturn Alley', 'London', 'England', 'SW1A2', '020-794-0032', 'info@borginandburkes.co.uk'),
('MT001', 'Metalworking of Thror', 'Forge Lane 1', 'Erebor', 'Lonely Mountain', '00010', '123-456-7890', 'thror@dwarvinforager.com'),
('UDHE001', 'Radiant Resources', 'Subterranean Outpost 5', 'Inner City', 'Hollow Earth', '00000', '000-000-0000', 'contact@hollowearthuranium.com'),
('OOH001', 'Odin"s Ocean Harvest', 'Valhalla Lane 9', 'Asgard', 'Midgard', '99900', '154-743-2456', 'contact@odinsocean.me'),
('ARM001', 'The Armorer', 'Forge Location 1', 'Nevarro', 'Mandalore Sector', '46785', '000-111-2222', 'armorer@mandalore.com'),
('NOL001', 'Noldorin Elven-smiths', 'Forge Street', 'Eregion', 'Middle-Earth','54321', '000-123-4567', 'Celebrimbor@elvensmiths.com'),
 ('MAAT001', 'Ma''at Ancient Remedies', '123 Nile St', 'Cairo', 'Egypt', '78531', '002-123-4567', 'contact@maatremedies.eg'),
 ('RFWI', 'Royal Family Wind Instruments', '123 Hyrule Castle', 'Hyrule City', 'Hyrule', '77777', '000-555-1234', 'contact@rfwi-hyrule.com'),
 ('HBA01', 'Hades Battle Axes', '123 Spartan Way', 'Sparta', 'Greece', '23100', '210-123-4567', 'contact@hadesaxes.gr'),
 ('CCI01', 'Crystal Caves Ilum', '001 Icebound Caves', '7G Sector', 'Unknown', '00011', '101-111-1000', 'info@crystalilum.com');


-- product data
-- insert some rows into the Product table
INSERT INTO product (vendorid, partnumber, name, price, unit, PhotoPath)
VALUES (3, 'BW001', 'Bottled Water', 1.50, 'bottle', 'assets/bottledWater.jpg'),
(4, 'AT101', 'Apple Tree', 29.99, 'tree', 'assets/appleTree.jpg'),
(1, 'BS100', 'Bird Seed', 5.99, 'bag', 'assets/birdSeed.jpg'),
(6, 'GDS001', 'Gold Dragon Statue', 120.00, 'piece', 'assets/goldSmaug.jpg'),
(7, 'UR238', 'Uranium', 75.50, 'gram', 'assets/uranium.jpg'),
(8, 'SR001', 'Stegt rødspætte', 15.99, 'portion', 'assets/friedFish.jpg'),
(12, 'OC1234', 'Ocarina', 49.99, 'pcs', 'assets/ocarina.jpg'),
(5, 'CPN001', 'Cursed Prosthetic Nose', 19.99, 'pcs', 'assets/prostheticNose.jpg'),
(9, 'BESK001', 'Beskar Chain Mail', 500.00, 'set', 'assets/chainmail.jpg'),
(10, 'RING1', 'One Ring', 999.99, 'each', 'assets/oneRing.jpg'),
(11, 'MAR101', 'Ostrich Feather', 15.99, 'each', 'assets/feather.jpg'),
(2, 'OBB234', 'Enzymatic IPA', 20.00, 'pint', 'assets/beer.jpg'),
(13, 'SGA345', 'Double-sided Axe', 500.99, 'pcs', 'assets/axe.jpg'),
(14, 'KYB123', 'Kyber Crystal', 199.99, 'each', 'assests/crystal.jpg');




-- Request data
INSERT INTO request (UserID, Description, Justification, DateNeeded, DeliveryMode, SubmittedDate)
VALUES (4, 'Order for more gold', 'Increase treasure hoard', '2941-08-08', 'Rope and Pulley', '2024-04-24'),
(6, 'Order for Dinner', 'Will attack if Hungry', '1200-04-30', 'Underwater Delivery', '2024-04-24'),
(7, 'Mission to save princess', 'Trying to Save Zelda', '1410-03-25', 'Pickup', '2024-04-24'),
(8, 'New nose for Dark Lord', 'Facial reconstruction', '1991-05-01', 'Magic Courier', '2024-04-24'),
(1, 'Water for Serpent', 'To sustain environment', '1788-05-10', 'Mystical Rainfall', '2024-04-24'),
(10, 'Order of 19 rings', 'To establish societal order', '2010-02-01', 'Discreet Distribution', '2024-04-24'),
(10, 'Order of 1 ring', 'To Rule Them All', '2010-02-01', 'Discreet Pickup', '2024-04-24'),
(2, 'Replacement trees', 'Replenishing Forest' , '0900-06-06', 'Nature-aligned delivery', '2024-04-24'),
(3, 'Replenishing Birdseed' , 'Caring for woodland creatures', '1000-07-02', 'Enchanted Eagle', '2024-04-24'),
(5, 'Uranium for Godzilla', 'Will Attack if Hungry', '1956-04-20', 'Subterranean Convoy', '2024-04-24'),
(9, 'Custom chainmail', 'Gift for a friend', '1100-01-03', 'Galactic Express', '2024-04-24'),
(11, 'Ostrich feather for Anubis', 'To weigh souls fairly', '1650-06-20', 'Mail', '2024-04-24'),
(12, 'Beer for Sasquatch', 'For leisurely people-watching', '2024-06-15', 'Stealthy Woodland Drop', '2024-04-24'),
(13, 'Double-sided axe for Kratos', 'For enhanced combat', '2005-11-23', 'Pickup', '2024-04-24'),
(14, 'New Kyber Crystal', 'Cracked destroying Temple', '2424-05-11', 'Starfleet', '2024-04-24');


INSERT INTO lineitem (RequestId, ProductId, Quantity)
VALUES (1, 4, 1),
(2, 6, 3),
(3, 7, 1),
(4, 8, 1),
(5, 1, 20),
(6, 10, 19),
(7, 10, 1),
(8, 2, 3),
(9, 3, 3),
(10, 5, 1),
(11, 9, 1),
(12, 11, 1),
(13, 12, 1), 
(14, 13, 1);